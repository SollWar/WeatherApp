package ru.nikita.weatherapp.ui.screens.main.models

sealed class MainScreenEvent {
    data object ReloadForecast: MainScreenEvent()
}