package com.example.hewa.domain.repository

import com.example.hewa.domain.models.CurrentWeather

/**
 *Weather repo contract class with a function to get weather data
 */
interface WeatherRepository {
    /*
    *Since its a network call use suspend to switch from UI thread to another thread
    */
    suspend fun getWeatherData(city: String = "Dar es Salaam"): State<CurrentWeather>
}