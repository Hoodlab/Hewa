package com.example.hewa.ui.components

import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.progressSemantics
import androidx.compose.material.MaterialTheme
import androidx.compose.material.ProgressIndicatorDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.TileMode
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp


@Composable
fun WeatherLoadingIndicator() {
    val gradientColors = listOf(
        Color(0xFF3AE180).copy(alpha = .4f),
        Color(0xFF27AE60).copy(alpha = .2f),
        Color(0xFFFFA500),
    )
    val gradient = Brush.linearGradient(
        colors = gradientColors,
    )
    Indicator(
        brush = gradient
    )

}

@Composable
fun Indicator(
    size: Dp = 32.dp,
    sweepAngle: Float = 337.5f,
    brush: Brush = Brush.linearGradient(listOf(MaterialTheme.colors.primary)),
    strokeWidth: Dp = ProgressIndicatorDefaults.StrokeWidth,
) {
    val transition = rememberInfiniteTransition()

    val currentArcStartAngle by transition.animateValue(
        0,
        360,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = 1500,
                easing = LinearEasing
            )
        )
    )
    val currentArc2StartAngle by transition.animateValue(
        0,
        360,
        Int.VectorConverter,
        infiniteRepeatable(
            animation = tween(
                durationMillis = 1100,
                easing = LinearEasing
            )
        )
    )

    // define stroke with given width and arc ends type considering device DPI
    val stroke = with(LocalDensity.current) {
        Stroke(width = strokeWidth.toPx(), cap = StrokeCap.Round)
    }

    // draw on canvas
    Canvas(
        Modifier
            .progressSemantics() // (optional) for Accessibility services
            .size(size) // canvas size
            .padding(strokeWidth / 2) //padding. otherwise, not the whole circle will fit in the canvas
    ) {
        drawArc(
            brush = brush,
            startAngle = currentArcStartAngle.toFloat() - 360,
            sweepAngle = sweepAngle,
            useCenter = false,
            style = stroke
        )
        // draw arc with the same stroke
        drawArc(
            color = Color(0xFF3AE180) ,
            startAngle = currentArcStartAngle.toFloat()-360 ,
            sweepAngle = 11.25f,
            useCenter = false,
            style = stroke
        )
    }
}

@Preview
@Composable
fun PrevIndicator() {
    val gradientColors = listOf(
        Color(0xFF3AE180).copy(alpha = .4f),
        Color(0xFF27AE60).copy(alpha = .2f),
        Color(0xFFFFA500),
    )
    val gradient = Brush.linearGradient(
        colors = gradientColors,
    )
    Indicator(
        brush = gradient
    )
}

