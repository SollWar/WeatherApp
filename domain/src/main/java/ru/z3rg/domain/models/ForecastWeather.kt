package ru.z3rg.domain.models

data class ForecastWeather(
    val cityName: String = "",
    val cityCord: String = "",
    val forecastCurrent: ForecastWeatherDay = ForecastWeatherDay(),
    val forecastDay: ArrayList<ForecastWeatherDay> = arrayListOf(),
    val error: String? = null
) {
    fun addForecastDay(forecastWeatherDay: ForecastWeatherDay) {
        forecastDay.add(forecastWeatherDay)
    }
}


