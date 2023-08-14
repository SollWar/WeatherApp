package ru.nikita.weatherapp.ui.screens.main.models

import ru.z3rg.domain.models.ForecastWeather

sealed class MainScreeViewState {
    data class Loading(
        val cityName: String
    ): MainScreeViewState()
    data class Display(
        val forecast: ForecastWeather = ForecastWeather()
    ): MainScreeViewState()
    object Error: MainScreeViewState()
    object Start: MainScreeViewState()
}