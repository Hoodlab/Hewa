package com.example.hewa.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.hewa.R
import com.example.hewa.data.api.ApiConstants
import com.example.hewa.domain.models.CurrentWeather
import com.example.hewa.domain.repository.State
import com.example.hewa.ui.components.WeatherBackground
import com.example.hewa.ui.components.WeatherLoadingIndicator
import com.example.hewa.ui.splashScreen.Logo
import com.example.hewa.ui.theme.DarBlue500
import com.example.hewa.ui.theme.Gold
import com.example.hewa.utils.Utils

enum class BottomNavScreen(@DrawableRes val icon: Int) {
    Home(icon = R.drawable.ic_home_start),
    Search(icon = R.drawable.ic_search),
    Back(icon = R.drawable.ic_back),
    Result(icon = R.drawable.ic_cloud)
}

@Composable
fun HomeScreen(homeViewModel: HomeViewModel, modifier: Modifier) {
    val state = homeViewModel.state
    /*
    * get the weather data once when the home screen enters composition
    * a side effect is used to avoid multiple api request when recomposition happens
    */
    LaunchedEffect(key1 = Unit) {
        homeViewModel.fetchWeatherData()
    }
    WeatherBackground {
        when (state.weather) {
            is State.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
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
                    modifier
                )
            }
        }
    }
}

@Composable
fun WeatherDetails(
    weather: CurrentWeather,
    modifier: Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier
                .padding(top = 30.dp)
                .fillMaxWidth()
        ) {
            Logo(
                style = MaterialTheme.typography.h4,
                modifier = Modifier
                    .padding(top = 15.dp)
                    .size(11.dp)
            )
            CurrentWeatherDetail(weather)
            WeatherInfo(modifier = Modifier, weather)
            FeelsLikeItem(weather)
            WeatherSummary(weather)
        }
    }

}

@Composable
private fun CurrentWeatherDetail(weather: CurrentWeather) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(top = 60.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.LocationOn,
            contentDescription = null,
            modifier = Modifier.size(30.dp),
            tint = Gold
        )
        Spacer(modifier = Modifier.size(6.dp))
        Text(
            text = weather.cityName,
            style = MaterialTheme.typography.h4,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
private fun FeelsLikeItem(weather: CurrentWeather) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Row {
            Text(text = "Feels like", color = MaterialTheme.colors.onPrimary)
            Spacer(modifier = Modifier.size(6.dp))
            Text(text = "${weather.feelsLike}℃", color = Gold)
        }
        Spacer(modifier = Modifier.size(8.dp))
        Text(
            text = weather.weatherCondition,
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
fun BottomNavBar(selectedScreen: BottomNavScreen, onSelected: (BottomNavScreen) -> Unit) {
    BottomAppBar(
        backgroundColor = DarBlue500
    ) {
        BottomNavScreen.values().toMutableList().forEachIndexed { index, it ->
            if (index != BottomNavScreen.values().size - 1) {
                BottomNavigationItem(
                    selected = selectedScreen == it,
                    onClick = { onSelected.invoke(it) },
                    icon = {
                        val color =
                            if (selectedScreen == it) Gold else MaterialTheme.colors.onPrimary
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = null,
                            modifier = Modifier.size(26.dp),
                            tint = color
                        )
                    }
                )
            }
        }
    }
}

@Composable
fun WeatherInfo(modifier: Modifier = Modifier, weather: CurrentWeather) {
    Box(
        modifier = modifier
            .padding(top = 20.dp, end = 40.dp)
            .fillMaxWidth(), contentAlignment = Alignment.Center
    ) {
        AsyncImage(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .size(100.dp)
                .padding(end = 10.dp),
            model = ApiConstants.iconURl(weather.weatherCode),
            placeholder = painterResource(id = R.drawable.ic_cloud),
            alignment = Alignment.CenterStart,
            contentDescription = "",
            contentScale = ContentScale.Crop,
            onError = {
                it.result.throwable.printStackTrace()
            }
        )
        /* Icon(
             painter = painterResource(id = R.drawable.ic_cloud),
             contentDescription = null,
             modifier = Modifier
                 .align(Alignment.TopEnd)
                 .padding(end = 10.dp),
             tint = MaterialTheme.colors.onPrimary
         )*/
        Text(
            text = weather.temperature,
            fontSize = 130.sp,
            modifier = Modifier
                .align(Alignment.Center)
                .padding(end = 50.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary
        )
        Text(
            text = "\u2103",
            fontSize = 40.sp,
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(end = 40.dp, bottom = 20.dp),
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onPrimary
        )
    }
}

@Composable
fun WeatherSummary(weather: CurrentWeather) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .wrapContentSize(align = Alignment.BottomCenter)
            .padding(8.dp),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        WeatherInfoItem(
            icon = R.drawable.ic_wind_speed,
            title = "Wind",
            info = "${weather.windSpeed} km/h"
        )
        Spacer(modifier = Modifier.size(6.dp))
        WeatherInfoItem(
            icon = R.drawable.ic_date,
            title = Utils.getFormattedDate(),
            info = Utils.getFormattedTime()
        )
        Spacer(modifier = Modifier.size(6.dp))
        WeatherInfoItem(
            icon = R.drawable.ic_humidity,
            title = "Humidity",
            info = "${weather.humidity} %"
        )
        Spacer(modifier = Modifier.size(6.dp))
    }
}

@Composable
fun WeatherInfoItem(@DrawableRes icon: Int, title: String, info: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = MaterialTheme.colors.onPrimary,
                modifier = Modifier.size(30.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
            Text(
                text = title,
                color = MaterialTheme.colors.onPrimary,
                fontWeight = FontWeight.Bold,
                style = MaterialTheme.typography.h6
            )
        }
        Text(
            text = info,
            modifier = Modifier.padding(end = 8.dp),
            color = MaterialTheme.colors.onPrimary,
            fontWeight = FontWeight.Bold,
            style = MaterialTheme.typography.h6
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun PrevHomeScreen() {

}
