package com.group.bottombarview.ui.view

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.group.bottombarview.ui.screen.Screen
import com.group.bottombarview.ui.theme.BottomBarViewTheme

data class BottomButtonModel(
    val icon: ImageVector,
    val title: String,
    val selectedColor: Color,
    val defaultColor: Color
)

@Composable
fun CustomBottomNavigationBar(
    modifier: Modifier = Modifier,
    screens: List<Screen>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit,
    navigationBarHeight: Dp = 80.dp,
    backGroundCircleSize: Float = 40f,
    backGroundCircleColor: Color = Color.Yellow,
    ) {

    val itemCenters = remember { mutableStateListOf<Offset>() }
    val animatedOffset = if (itemCenters.isNotEmpty()) {
        remember { Animatable(itemCenters[0], Offset.VectorConverter) }
    } else {
        null
    }

    val animatedSize = remember { Animatable(backGroundCircleSize) }

    if (itemCenters.isNotEmpty())
        LaunchedEffect(selectedIndex) {
            val targetOffset = itemCenters[selectedIndex]
            animatedSize.animateTo(
                targetValue = backGroundCircleSize / 2,
                animationSpec = tween(durationMillis = 100, easing = LinearOutSlowInEasing)
            )
            animatedOffset?.animateTo(
                targetValue = targetOffset,
                animationSpec = tween(durationMillis = 200, easing = LinearOutSlowInEasing)
            )
            animatedSize.animateTo(
                targetValue = backGroundCircleSize,
                animationSpec = tween(durationMillis = 100, easing = LinearOutSlowInEasing)
            )
        }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(navigationBarHeight)
            .background(Color.Blue),
    ) {
        // Background circle
        animatedOffset?.let {
            val animatedOffsetX = animatedOffset.value.x
            val halfAnimatedSize = animatedSize.value * 1.3
            val x = (animatedOffsetX - halfAnimatedSize).toInt()
            val animatedOffsetY = animatedOffset.value.y
            val y = (animatedOffsetY - halfAnimatedSize).toInt()

            Box(
                modifier = Modifier
                    .offset { IntOffset(x = x, y = y) }
                    .size(animatedSize.value.dp)
                    .background(backGroundCircleColor, shape = CircleShape)
            )
        }

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            screens.forEachIndexed { index, button ->
                Button(
                    index,
                    itemCenters,
                    screens.size,
                    onItemSelected,
                    selectedIndex,
                    button.model
                )
            }
        }
    }
}

@Composable
private fun Button(
    index: Int,
    itemCenters: SnapshotStateList<Offset>,
    itemCount: Int,
    onItemSelected: (Int) -> Unit,
    selectedIndex: Int,
    buttonModel: BottomButtonModel
) {
    val yCenters = remember { mutableStateOf<Float?>(null) }
    val xCenters = remember { mutableStateOf<Float?>(null) }

    if (xCenters.value != null && yCenters.value != null) {
        val center = Offset(xCenters.value!!, yCenters.value!!)
        if (itemCenters.size < itemCount) {
            itemCenters.add(center)
        } else {
            itemCenters[index] = center
        }
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .onGloballyPositioned { coordinates ->
                val position = coordinates.positionInParent()
                val positionX = position.x
                val sizeW = coordinates.size.width
                val x = positionX + sizeW / 2f
                xCenters.value = x
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Column(
            modifier = Modifier
                .onGloballyPositioned { coordinates ->
                    val position = coordinates.positionInParent()
                    val positionY = position.y
                    val sizeH = coordinates.size.height
                    val y: Float = positionY + sizeH / 2f
                    yCenters.value = y
                }
                .clickable {
                    onItemSelected(index)
                },
        ) {
            Icon(
                imageVector = buttonModel.icon,
                contentDescription = "",
                tint = if (selectedIndex == index) buttonModel.selectedColor else buttonModel.defaultColor,
                modifier = Modifier.size(24.dp)
            )
        }
        if (buttonModel.title.isNotEmpty()) {
            Spacer(Modifier.height(6.dp))

            Text(
                text = buttonModel.title,
                fontSize = 12.sp,
                color = if (selectedIndex == index) buttonModel.selectedColor else buttonModel.defaultColor,
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun CustomBottomNavigationBarPreview() {
    val screens = listOf(Screen.Home, Screen.Search, Screen.Profile)
    BottomBarViewTheme {
        CustomBottomNavigationBar(
            screens = screens,
            selectedIndex = 0,
            onItemSelected = {}
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun BottomBarViewThemeWithEmptyTextPreview() {
    val screens = listOf(Screen.HomeWithOutText, Screen.SearchWithOutText, Screen.ProfileWithOutText)
    BottomBarViewTheme {
        CustomBottomNavigationBar(
            screens = screens,
            selectedIndex = 0,
            onItemSelected = {}
        )
    }
}
