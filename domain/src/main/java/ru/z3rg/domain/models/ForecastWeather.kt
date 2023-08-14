package ru.z3rg.domain.models

data class ForecastWeather(
    val cityName: String = "",
    val forecastDay: ArrayList<ForecastWeatherDay> = arrayListOf()
) {
    fun addForecastDay(forecastWeatherDay: ForecastWeatherDay) {
        forecastDay.add(forecastWeatherDay)
    }
}


