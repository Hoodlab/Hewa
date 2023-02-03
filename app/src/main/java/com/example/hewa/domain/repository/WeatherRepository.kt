package com.example.hewa.domain.repository

import com.example.hewa.domain.models.CurrentWeather


interface WeatherRepository {
    suspend fun getWeatherData(city:String = "Dar es Salaam"): State<CurrentWeather>
}