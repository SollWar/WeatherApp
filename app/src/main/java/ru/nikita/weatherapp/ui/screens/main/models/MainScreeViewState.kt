package ru.nikita.weatherapp.ui.screens.main.models

sealed class MainScreeViewState {
    object Loading: MainScreeViewState()
    object Error: MainScreeViewState()
    data class Display(
        val cityName: String
    ): MainScreeViewState()
}