package com.example.hewa.data.api.mapers


import com.example.hewa.data.api.models.WeatherApi
import com.example.hewa.domain.models.CurrentWeather
import javax.inject.Inject

class ApiWeatherMapperImpl @Inject constructor() : ApiWeatherMapper<CurrentWeather, WeatherApi> {
    override fun mapToDomain(apiEntity: WeatherApi): CurrentWeather {
        return CurrentWeather(
            cityName = apiEntity.name,
            temperature = apiEntity.main.temp.toInt().toString(),
            weatherCondition = apiEntity.weather[0].main,
            windSpeed = apiEntity.wind.speed.toInt().toString(),
            weatherCode = apiEntity.weather[0].icon,
            humidity = apiEntity.main.humidity.toString(),
            feelsLike = apiEntity.main.feelsLike.toInt().toString()
        )
    }
}