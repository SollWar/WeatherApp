package ru.nikita.weatherapp.ui.screens.search.models

sealed class SearchScreenEvent {
    data class UpdateTextField(
        val textFieldValue: String = ""
    ): SearchScreenEvent()
    data object UpdateCityList: SearchScreenEvent()
    data class UpdateCityNameDataStore(
        val cityName: String = ""
    ): SearchScreenEvent()
}