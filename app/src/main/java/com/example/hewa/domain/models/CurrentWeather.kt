package com.example.hewa.domain.models

data class CurrentWeather(
    val cityName:String,
    val temperature:String,
    val weatherCondition:String,
    val windSpeed:String,
    val weatherCode:String,
    val humidity:String,
    val feelsLike:String
)