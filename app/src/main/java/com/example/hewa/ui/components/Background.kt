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
/**
*A background function that can be used on different screens
 * @param[modifier] modifier
 * @param[content] a lambda function that allows other UI function to be used
*/
@Composable
fun WeatherBackground(modifier: Modifier = Modifier,content: @Composable () -> Unit) {
    // gradient brush for the bg
    val brush = Brush.linearGradient(listOf(
        Color(0xff005C97),
        Color(0xff363795)
    ))
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(brush)
            .drawBehind {
                //get the data necessary to draw the bg
                val path = Path()
                val x = size.width
                val y = size.height
                val center = size.center

                path.apply {
                    //start at the bottom left conner
                    moveTo(0f, y)
                    //create a strait line to draw the bottom part of the ui
                    lineTo(-20f, y * .7f)
                    //create a curved smooth line that is connecting btn two edges
                    // by using percentages we can easily determine the position
                    // of the points to be ploted
                    cubicTo(
                        x1 = x * .8f,
                        y1 = y * .6f,
                        x2 = x * .4f,
                        y2 = y * .8f,
                        x3 = x + center.x,
                        y3 = y * .65f,
                    )
                    //finish the drawing by connecting the left and write edge
                    lineTo(x, y)
                }
                // create a different gradient to use on the surface drawing
                val gradient = Brush.linearGradient(
                    colors = listOf(Color(0xFF1F4696), Color(0xFF005C97))
                )
                drawPath(path = path, brush = gradient)
            }
    ) { content() } // pass in reusable contents
}

@Preview(showSystemUi = true)
@Composable
fun PrevBackground() {
    WeatherBackground {
        Text(text = "Weather")
    }
}
