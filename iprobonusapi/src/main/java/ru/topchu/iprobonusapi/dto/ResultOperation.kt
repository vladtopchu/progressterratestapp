package ru.topchu.iprobonusapi.dto

data class ResultOperation(
    val status: Int,
    val message: String? = null,
    val messageDev: String? = null,
    val codeResult: Int,
    val duration: Double,
    val idLog: String
)