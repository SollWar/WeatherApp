package ru.nikita.weatherapp.ui.screens.search.models

import ru.z3rg.domain.models.CityList

data class SearchScreenState(
    var cityList: CityList = CityList(),
    var textFieldValue: String = ""
)