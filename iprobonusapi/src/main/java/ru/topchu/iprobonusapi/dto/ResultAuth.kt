package ru.topchu.iprobonusapi.dto

data class ResultAuth(
    val result: ResultOperation,
    val accessToken: String? = null
)