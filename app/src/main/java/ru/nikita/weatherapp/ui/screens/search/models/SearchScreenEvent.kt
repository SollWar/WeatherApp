package ru.nikita.weatherapp.ui.screens.search.models

sealed class SearchScreenEvent {
    data class UpdateTextField(
        val textFieldValue: String = ""
    ): SearchScreenEvent()
    data object UpdateCityList: SearchScreenEvent()
    data class UpdateCityDataStore(
        val cityName: String = "",
        val lat: Double = 0.0,
        val lon: Double = 0.0
    ): SearchScreenEvent()
}