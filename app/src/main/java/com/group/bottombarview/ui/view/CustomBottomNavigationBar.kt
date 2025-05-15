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
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.layout.positionInRoot
import androidx.compose.ui.platform.LocalDensity
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
    val density = LocalDensity.current
    val itemCount = items.size

    // Зберігаємо позиції центрів кнопок
    val itemCenters = remember { mutableStateListOf<Offset>() }

    // Анімована позиція кульки
    val animatedOffset = remember { Animatable(Offset.Zero, Offset.VectorConverter) }

    // Анімований розмір кульки
    val animatedSize = remember { Animatable(60f) }

    // Оновлюємо позицію та розмір при зміні вибраного індексу
    LaunchedEffect(selectedIndex, itemCenters) {
        if (itemCenters.size == itemCount) {
            val targetOffset = itemCenters[selectedIndex]
            animatedOffset.animateTo(
                targetOffset,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
            animatedSize.animateTo(
                80f,
                animationSpec = tween(durationMillis = 300, easing = LinearOutSlowInEasing)
            )
        }
    }

    Box(
        modifier = modifier
            .fillMaxWidth()
            .height(80.dp)
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        // Рядок з кнопками
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            items.forEachIndexed { index, item ->
                Box(
                    modifier = Modifier
                        .size(60.dp)
                        .onGloballyPositioned { coordinates ->
                            val position = coordinates.positionInRoot()
                            val size = coordinates.size
                            val center = Offset(
                                x = position.x + size.width / 2f,
                                y = position.y + size.height / 2f
                            )
                            if (itemCenters.size < itemCount) {
                                itemCenters.add(center)
                            } else {
                                itemCenters[index] = center
                            }
                        }
                        .clickable {
                            onItemSelected(index)
                        },
                    contentAlignment = Alignment.Center
                ) {
                    // Фонова кулька для вибраної кнопки
                    if (selectedIndex == index) {
                        Box(
                            modifier = Modifier
                                .size(animatedSize.value.dp)
                                .background(Color.LightGray, shape = CircleShape)
                        )
                    }

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

        // Анімована кулька, що переміщується між кнопками
        if (itemCenters.size == itemCount) {
            Box(
                modifier = Modifier
                    .offset {
                        IntOffset(
                            x = (animatedOffset.value.x - animatedSize.value / 2).toInt(),
                            y = (animatedOffset.value.y - animatedSize.value / 2).toInt()
                        )
                    }
                    .size(animatedSize.value.dp)
                    .background(Color.LightGray, shape = CircleShape)
            )
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
