package ru.topchu.iprobonusapi.dto

data class AvaliableBonusesData(
    val typeBonusName: String? = null,
    val currentQuantity: Double,
    val forBurningQuantity: Double,
    val dateBurning: String
)