package com.example.hewa.ui.results

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.hewa.domain.repository.State
import com.example.hewa.ui.components.WeatherBackground
import com.example.hewa.ui.components.WeatherLoadingIndicator
import com.example.hewa.ui.home.WeatherDetails

@Composable
fun ResultScreen(state: ResultState, modifier: Modifier) {
    WeatherBackground {
        when (state.weather) {
            is State.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    WeatherLoadingIndicator()
                }
            }

            is State.Error -> {
                Text(
                    text = "An Error Occurred",
                    modifier
                        .fillMaxSize()
                        .wrapContentSize(align = Alignment.Center),
                    color = MaterialTheme.colors.error
                )
            }

            is State.Success -> {
                WeatherDetails(
                    weather = state.weather.data!!,
                    modifier = modifier
                )
            }
        }
    }
}