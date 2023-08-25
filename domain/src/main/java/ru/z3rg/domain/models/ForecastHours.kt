package ru.z3rg.domain.models

data class ForecastHours(
    val time: String,
    val tempC: Double,
    val conditionText: String = "",
    val conditionIcon: String = "",
    val windKhp: Double,
    val humidity: Double,
    val chanceOfRain: Double
)