package com.example.hewa.data.api


import retrofit2.http.GET
import retrofit2.http.Query
/**
*A Service class used to query the weather Api
 */
interface WeatherApiService {
    @GET(ApiConstants.END_POINT)
    suspend fun getWeatherData(
        @Query(ApiParameters.SEARCHPARAM) city:String = "Dar es Salaam",
        @Query(ApiParameters.APPID) key:String = ApiConstants.API_KEY,
        @Query("units") units:String = "metric"
    ):com.example.hewa.data.api.models.WeatherApi
}