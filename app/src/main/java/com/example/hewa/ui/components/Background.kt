package com.example.hewa.ui.components


import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.center
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.Fill
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.core.graphics.PathParser

@Composable
fun WeatherBackground(modifier: Modifier = Modifier,content: @Composable () -> Unit) {
    val brush = Brush.linearGradient(listOf(
        Color(0xff005C97),
        Color(0xff363795)
    ))
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush)
            .drawBehind {
                val path = Path()
                val x = size.width
                val y = size.height
                val center = size.center

                path.apply {
                    moveTo(0f, y)
                    lineTo(-20f, y * .7f)
                    cubicTo(
                        x1 = x * .8f,
                        y1 = y * .6f,
                        x2 = x * .4f,
                        y2 = y * .8f,
                        x3 = x + center.x,
                        y3 = y * .65f,
                    )
                    lineTo(x, y)
                }
                val gradient = Brush.linearGradient(
                    colors = listOf(Color(0xFF1F4696), Color(0xFF005C97))
                )
                drawPath(path = path, brush = gradient)
            }
    ) { content() }
}

@Composable
fun BG() {
    Canvas(modifier =
        Modifier.fillMaxSize()){
        val path = Path()
        val x = size.width
        val y = size.height
        val center = size.center

        path.apply {
            moveTo(0f, y )
            lineTo(-20f, center.y + center.y /2)
            cubicTo(
                x1 = center.x /2,
                y1 = y + center.y / 2 ,
                x2 = center.x,
                y2 = center.y + center.y / 3,
                x3 = x + center.x,
                y3 = y
            )
            lineTo(x, y)
        }

        drawPath(path = path, color = Color.Cyan)
    }
}

@Composable
fun BG2() {
    Canvas(modifier = Modifier.size(width = 605.dp, height = 100.dp)) {
        val path = Path()
        val x = size.width
        val y = size.height
        val center = size.center

        path.apply {
            moveTo(0f, y)
            lineTo(-20f, y * .7f)
            cubicTo(
                x1 = x * .8f,
                y1 = y * .6f,
                x2 = x * .4f,
                y2 = y * .8f,
                x3 = x + center.x,
                y3 = y * .65f,
            )
            lineTo(x, y)
        }
        val gradient = Brush.linearGradient(
            colors = listOf(Color(0xFF1F4696), Color(0xFF005C97))
        )
        drawPath(path = path, brush = gradient)

    }
}


@Preview(showSystemUi = true)
@Composable
fun PrevBackground() {
    WeatherBackground {
        Text(text = "Weather")
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevBG() {
    BG2()
}

/*
*val path = Path()
        val x = size.width
        val y = size.height
        val center = size.center

        path.apply {
            moveTo(0f, y)
            lineTo(-20f, y * .7f)
            cubicTo(
                x1 = x * .8f,
                y1 = y * .6f,
                x2 = x * .1f,
                y2 = y * .8f,
                x3 = x + center.x,
                y3 = y * .65f,
            )
            lineTo(x, y)
        }
        val gradient = Brush.linearGradient(
            colors = listOf(Color(0xFF1F4696), Color(0xFF005C97))
        )
        drawPath(path = path, brush = gradient)
*/