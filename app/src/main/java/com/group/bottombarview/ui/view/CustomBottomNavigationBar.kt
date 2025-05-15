package com.group.bottombarview.ui.view

import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.VectorConverter
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInParent
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.layout.positionOnScreen
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.group.bottombarview.ui.screen.Greeting
import com.group.bottombarview.ui.theme.BottomBarViewTheme

@Composable
fun CustomBottomNavigationBar(
    modifier: Modifier = Modifier,
    items: List<String>,
    icons: List<ImageVector>,
    selectedIndex: Int,
    onItemSelected: (Int) -> Unit
) {
    val itemCount = items.size
    val itemCenters = remember { mutableStateListOf<Offset>() }
    val animatedOffset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }
    val size = 50f
    val animatedSize = remember { Animatable(size) }

    LaunchedEffect(selectedIndex) {
        if (itemCenters.size == itemCount) {
            val targetOffset = itemCenters[selectedIndex]

            // 1. Зменшення кульки
            animatedSize.animateTo(
                targetValue = size / 2,
                animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
            )

            // 2. Переміщення кульки
            animatedOffset.animateTo(
                targetValue = targetOffset,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )

            // 3. Збільшення кульки
            animatedSize.animateTo(
                targetValue = size,
                animationSpec = tween(durationMillis = 150, easing = LinearOutSlowInEasing)
            )
        }
    }
    val height = 70

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(height.dp)
            .background(Color.Blue),
    ) {
        if (itemCenters.size == itemCount) {
            val animatedOffsetX = animatedOffset.value.x
            val halfAnimatedSize = animatedSize.value * 1.3

            val x = (animatedOffsetX - halfAnimatedSize).toInt()

            val animatedOffsetY = animatedOffset.value.y
            val y = (animatedOffsetY - halfAnimatedSize).toInt()

            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(x = x, y = y)
                    }
                    .size(animatedSize.value.dp)
                    .background(Color.Red, shape = CircleShape)
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEachIndexed { index, item ->
                Column(
                    modifier = Modifier
                        .size(60.dp)
                        .onGloballyPositioned { coordinates ->
                            val position = coordinates.positionInParent()
                            val positionX = position.x
                            val positioY = position.y
                            val sizeW = coordinates.size.width
                            val sizeH = coordinates.size.height
                            val x = positionX + sizeW / 2f
                            val y = positioY + sizeH / 2f

                            val center = Offset(x,y)
                            if (itemCenters.size < itemCount) {
                                itemCenters.add(center)
                            } else {
                                itemCenters[index] = center
                            }
                        }
                        .clickable {
                            onItemSelected(index)
                        },
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    // Іконка
                    Icon(
                        imageVector = icons[index],
                        contentDescription = item,
                        tint = if (selectedIndex == index) Color.Black else Color.Gray,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BottomBarViewTheme {
        Greeting("Android")
    }
}
