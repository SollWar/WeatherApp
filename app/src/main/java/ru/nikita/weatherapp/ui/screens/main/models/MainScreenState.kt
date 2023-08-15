package ru.nikita.weatherapp.ui.screens.main.models

import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.models.ForecastWeatherDay

data class MainScreenState(
    var forecast: ForecastWeather = ForecastWeather(
        forecastDay = arrayListOf(
            ForecastWeatherDay(),
            ForecastWeatherDay(),
            ForecastWeatherDay(),
            ForecastWeatherDay(),
            ForecastWeatherDay()
        )
    ),
    var currentDate: String = "",
    var loading: Boolean = false,
    var error: Boolean = false
)