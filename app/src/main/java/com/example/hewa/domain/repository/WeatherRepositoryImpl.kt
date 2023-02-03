package com.example.hewa.domain.repository

import com.example.hewa.data.api.WeatherApiService
import com.example.hewa.data.api.mapers.ApiWeatherMapper
import com.example.hewa.data.api.mapers.ApiWeatherMapperImpl
import com.example.hewa.data.api.models.WeatherApi
import com.example.hewa.domain.models.CurrentWeather
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApiService,
    private val apiWeatherMapper: ApiWeatherMapperImpl,
) : WeatherRepository {
    override suspend fun getWeatherData(city: String): State<CurrentWeather> {
        return try {
            val apiWeather = weatherApi.getWeatherData(city)
            val weather = apiWeatherMapper.mapToDomain(apiWeather)
            State.Success(weather)
        } catch (e: Exception) {
            e.printStackTrace()
            State.Error(e.message.orEmpty())
        }
    }


}