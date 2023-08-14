package ru.z3rg.domain.models

data class ForecastWeatherDay(
    val conditionText: String,
    val conditionIcon: String,
    val avgTempC: Double,
    val minTempC: Double,
    val maxTempC: Double,
    val maxWindKph: Double,
    val avgHumidity: Double,
    val chanceRain: Double,
    val date: String
)
