package com.example.hewa.ui.splashScreen

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.hewa.ui.components.WeatherBackground
import com.example.hewa.ui.theme.Gold
import kotlinx.coroutines.delay

@Composable
fun WeatherSplashScreen(
    modifier: Modifier = Modifier,
    onTimeOut: () -> Unit,
) {
    WeatherBackground {
        Box(
            modifier = modifier
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            val currentTimeOut by rememberUpdatedState(newValue = onTimeOut)
            LaunchedEffect(key1 = Unit) {
                delay(1500)
                currentTimeOut.invoke()
            }
            Logo(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(22.dp)
            )

        }
    }
}

@Composable
fun Logo(
    modifier: Modifier = Modifier,
    logoWeight: FontWeight = FontWeight.Bold,
    style: TextStyle = MaterialTheme.typography.h2,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "Hewa",
            color = MaterialTheme.colors.onPrimary,
            style = style,
            fontWeight = logoWeight
        )
        Spacer(modifier = Modifier.width(6.dp))
        Surface(
            shape = CircleShape,
            modifier = modifier,
            color = Gold
        ) { }
    }

}

@Preview(uiMode = UI_MODE_NIGHT_YES, showSystemUi = true)
@Composable
fun PrevSplashScreen() {
    WeatherSplashScreen {

    }
}