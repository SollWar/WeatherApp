package ru.z3rg.domain.models

data class ForecastWeatherDay(
    val conditionText: String = "",
    val conditionIcon: String = "",
    val avgTempC: Double = 0.0,
    val minTempC: Double = 0.0,
    val maxTempC: Double = 0.0,
    val maxWindKph: Double = 0.0,
    val avgHumidity: Double = 0.0,
    val chanceRain: Double = 0.0,
    val date: String = "2013-11-11"
)
