package com.example.hewa.data.api.models


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Coord(
    @SerialName("lat")
    val lat: Double = 0.0,
    @SerialName("lon")
    val lon: Double = 0.0
)