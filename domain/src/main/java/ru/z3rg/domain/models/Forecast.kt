package ru.z3rg.domain.models

data class Forecast(
    var cityName: String,
    var forecastDay: List<ForecastDay>
)

data class ForecastDay(
    var conditionText: String,
    var conditionIcon: String,
    var avgTempC: Double,
    var maxWindKph: Int,
    var avgHumidity: Int,
    var chanceRain: Int
)
