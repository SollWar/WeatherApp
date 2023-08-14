package ru.z3rg.domain.models

data class ForecastWeather(
    var cityName: String = "",
    var forecastDay: ArrayList<ForecastWeatherDay> = arrayListOf()
) {
    fun addForecastDay(forecastWeatherDay: ForecastWeatherDay) {
        forecastDay.add(forecastWeatherDay)
    }
}


