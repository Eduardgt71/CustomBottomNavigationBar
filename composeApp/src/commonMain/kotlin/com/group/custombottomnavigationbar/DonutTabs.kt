package com.group.custombottomnavigationbar

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.group.bottomview.TabsModel
import com.group.bottomview.drawable.HomeIcon
import com.group.bottomview.drawable.Person
import com.group.bottomview.drawable.Search
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.roundToInt
import kotlin.math.sin

@Composable
fun DonutTabs(
    tabs: List<TabsModel>, // Список табів з іконками і назвами
    modifier: Modifier = Modifier, // Модифікатор для зовнішнього компонування
    donutThickness: Dp = 30.dp, // Товщина бублика
    onTabSelected: (Int) -> Unit = {} // Callback при виборі таба
) {
    val count = tabs.size
    if (count == 0) return // Якщо список порожній, нічого не малюємо

    val anglePerItem = 360f / count // Кут, який займає кожен сектор бублика
    var selectedIndex by remember { mutableStateOf(0) } // Вибраний таб

    // Обчислюємо обертання бублика так, щоб вибраний таб опинився внизу (нижній центр)
    val targetRotation = 90f - (selectedIndex * anglePerItem + anglePerItem / 2)
    val rotation by animateFloatAsState(
        targetValue = targetRotation, // Цільовий кут обертання
        animationSpec = tween(
            durationMillis = 600,
            easing = FastOutSlowInEasing
        ), // Анімація обертання
        label = "donutRotation"
    )

    // Контейнер для бублика та іконок
    Box(
        modifier = modifier
            .size(150.dp) // Фіксований розмір бублика
            .aspectRatio(1f), // Квадратна форма
        contentAlignment = Alignment.Center
    ) {

        // --- Малюємо бублик ---
        Canvas(modifier = Modifier.fillMaxSize().graphicsLayer { rotationZ = rotation }) {
            val strokeWidth = donutThickness.toPx() // Товщина лінії в пікселях
            val radius = size.minDimension / 2 - strokeWidth / 2 // Радіус бублика

            // Малюємо кожен сектор бублика
            tabs.forEachIndexed { index, _ ->
                drawArc(
                    color = Color.LightGray, // Колір сектора
                    startAngle = index * anglePerItem, // Початковий кут сектора
                    sweepAngle = anglePerItem, // Кут сектору
                    useCenter = false, // Без з'єднання до центру
                    style = Stroke(width = strokeWidth), // Товщина лінії
                    topLeft = Offset(center.x - radius, center.y - radius), // Позиція дуги
                    size = Size(radius * 2, radius * 2) // Розмір дуги
                )
            }
        }

        // --- Малюємо іконки ---
        tabs.forEachIndexed { index, tab ->
            val iconSize = 24.dp
            // Центр сектора в градусах
            val centerAngle = index * anglePerItem + anglePerItem / 2
            // Кут в радіанах
            val angleRad = (centerAngle * (PI / 180.0)).toFloat()

            // Відстань від центру кола до центру іконки
            val radius = with(LocalDensity.current) { 120.dp.toPx() } / 2

            // Розмір іконки в пікселях
            val iconSizePx = with(LocalDensity.current) { iconSize.toPx() }

            // Позиція центру іконки на колі з урахуванням обертання бублика
            val xCenter = cos(angleRad + rotation * (PI / 180f)) * radius
            val yCenter = sin(angleRad + rotation * (PI / 180f)) * radius

            // Зсув кожної іконки по її власному напрямку
            val xOffset = xCenter - cos(angleRad) / (iconSizePx)
            val yOffset = yCenter - sin(angleRad) / (iconSizePx)

            // Box для іконки, Icon завжди вертикальна
            Box(
                modifier = Modifier
                    .offset { IntOffset(xOffset.roundToInt(), yOffset.roundToInt()) }
                    .size(iconSize)
                    .clickable {
                        selectedIndex = index
                        onTabSelected(index)
                    },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    modifier = Modifier.size(iconSize),
                    imageVector = tab.icon,
                    contentDescription = tab.title,
                    tint = if (index == selectedIndex) Color.Black else Color.Gray
                )
            }
        }


    }
}

@Preview(showBackground = true)
@Composable
private fun DonutTabsPreview() {
    val tabs = listOf(
        TabsModel(HomeIcon, "Home"),
        TabsModel(Search, "Fav"),
        //TabsModel(HomeIcon, "Settings"),
        TabsModel(Person, "Profile"),
    )

    DonutTabs(
        tabs = tabs,
    )
}
