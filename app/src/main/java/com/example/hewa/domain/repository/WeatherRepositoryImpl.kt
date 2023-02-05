package com.example.hewa.domain.repository

import com.example.hewa.data.api.WeatherApiService
import com.example.hewa.data.api.mapers.ApiWeatherMapper
import com.example.hewa.data.api.mapers.ApiWeatherMapperImpl
import com.example.hewa.data.api.models.WeatherApi
import com.example.hewa.domain.models.CurrentWeather
import javax.inject.Inject

/**
 *A concrete implementation of a repository contract class
 * @param[weatherApi] a service to communicate with API
 * @param[apiWeatherMapper]a maps Api to Domain entity
 */
class WeatherRepositoryImpl @Inject constructor(
    private val weatherApi: WeatherApiService,
    private val apiWeatherMapper: ApiWeatherMapperImpl,
) : WeatherRepository {
    /**
     *gets weather data and return response from API which can be successful or failed
     * @param[city] the city to get the weather data
     * @return current weather data for the city selected
     */
    override suspend fun getWeatherData(city: String): State<CurrentWeather> {
        // send the request and get the result or catch errors returned if any
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