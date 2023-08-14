package ru.nikita.weatherapp.ui.screens.main.models

sealed class MainScreenEvent {
    object EnterScreen: MainScreenEvent()
    object LoadingData: MainScreenEvent()
    object LoadedData: MainScreenEvent()
    object ReloadedData: MainScreenEvent()
    object BackToScreen: MainScreenEvent()
    object SearchClick: MainScreenEvent()
}