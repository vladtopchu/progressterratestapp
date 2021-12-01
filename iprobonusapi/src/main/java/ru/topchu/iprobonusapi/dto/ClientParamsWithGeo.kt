package ru.topchu.iprobonusapi.dto

data class ClientParamsWithGeo(
    val idClient: String? = null,
    val accessToken: String? = null,
    val paramName: String? = null,
    val paramValue: String? = null,
    val latitude: Float,
    val longitude: Float,
    val sourceQuery: Int
)