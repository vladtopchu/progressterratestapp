package ru.topchu.iprobonusapi.dto

data class AvailableBonusesInfo(
    val resultOperation: ResultOperation,
    val data: AvailableBonusesData? = null
)