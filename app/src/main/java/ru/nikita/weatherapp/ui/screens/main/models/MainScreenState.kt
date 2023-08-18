package ru.nikita.weatherapp.ui.screens.main.models

import ru.z3rg.domain.models.ForecastWeather

sealed class MainScreenState {
    data class Display(
        var forecast: ForecastWeather = ForecastWeather(),
        var currentDate: String = "",
        var loading: Boolean = true,
        var error: Boolean = false
    ): MainScreenState()
    data object Error: MainScreenState()
    data object Loading: MainScreenState()
}