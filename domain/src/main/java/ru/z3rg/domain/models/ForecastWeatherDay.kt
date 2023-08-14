package ru.z3rg.domain.models

data class ForecastWeatherDay(
    var conditionText: String,
    var conditionIcon: String,
    var avgTempC: Double,
    var maxWindKph: Double,
    var avgHumidity: Double,
    var chanceRain: Double
)
