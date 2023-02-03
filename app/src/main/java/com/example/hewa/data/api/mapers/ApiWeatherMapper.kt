package com.example.hewa.data.api.mapers

interface ApiWeatherMapper<Domain, ApiEntity> {
    fun mapToDomain(apiEntity: ApiEntity): Domain
}