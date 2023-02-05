package com.example.hewa.data.api.mapers

/**
*A contract class for mapping api entities to domain entities
*/
interface ApiWeatherMapper<Domain, ApiEntity> {
    fun mapToDomain(apiEntity: ApiEntity): Domain
}