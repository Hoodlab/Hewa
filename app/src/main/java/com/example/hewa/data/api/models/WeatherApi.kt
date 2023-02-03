package com.example.hewa.data.api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherApi(
    @SerialName("base")
    val base: String = "",
    @SerialName("clouds")
    val clouds: Clouds = Clouds(),
    @SerialName("cod")
    val cod: Int = 0,
    @SerialName("coord")
    val coord: Coord = Coord(),
    @SerialName("dt")
    val dt: Int = 0,
    @SerialName("id")
    val id: Int = 0,
    @SerialName("main")
    val main: Main = Main(),
    @SerialName("name")
    val name: String = "",
    @SerialName("rain")
    val rain: Rain = Rain(),
    @SerialName("sys")
    val sys: Sys = Sys(),
    @SerialName("timezone")
    val timezone: Int = 0,
    @SerialName("visibility")
    val visibility: Int = 0,
    @SerialName("weather")
    val weather: List<Weather> = listOf(),
    @SerialName("wind")
    val wind: Wind = Wind()
)