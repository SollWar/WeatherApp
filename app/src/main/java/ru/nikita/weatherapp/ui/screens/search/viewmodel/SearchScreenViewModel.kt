package ru.nikita.weatherapp.ui.screens.search.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nikita.weatherapp.ui.screens.search.models.SearchScreenEvent
import ru.nikita.weatherapp.ui.screens.search.models.SearchScreenState
import ru.z3rg.domain.usecases.FindCityByCityNameUseCase
import ru.z3rg.domain.usecases.SaveCityNameToDataStoreUseCase
import ru.z3rg.domain.usecases.SaveCordToDataStoreUseCase
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val findCityByCityNameUseCase: FindCityByCityNameUseCase,
    private val saveCityNameToDataStoreUseCase: SaveCityNameToDataStoreUseCase,
    private val saveCordToDataStoreUseCase: SaveCordToDataStoreUseCase
): ViewModel() {

    private val _state: MutableStateFlow<SearchScreenState> = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    fun onEvent(searchScreenEvent: SearchScreenEvent) {
        when (searchScreenEvent) {
            is SearchScreenEvent.UpdateTextField -> {
                updateTextField(searchScreenEvent.textFieldValue)
            }
            is SearchScreenEvent.UpdateCityList -> {
                if (_state.value.textFieldValue != "") {
                    updateCityList()
                }
            }
            is SearchScreenEvent.UpdateCityDataStore -> {
                updateCityNameDataStore(searchScreenEvent.cityName)
                updateCityCordDataStore(searchScreenEvent.lat, searchScreenEvent.lon)
            }
        }
    }

    private fun updateTextField(textFieldValue: String) {
        _state.value = _state.value.copy(
            textFieldValue = textFieldValue
        )
    }

    private fun updateCityList() {
        viewModelScope.launch {
            _state.value = _state.value.copy(
                loading = true,
                error = false
            )

            val responseCityList= viewModelScope.async(Dispatchers.IO) {
                return@async findCityByCityNameUseCase.invoke(_state.value.textFieldValue)
            }

            if (responseCityList.await().body != null) {
                _state.value = _state.value.copy(
                    cityList = responseCityList.await().body!!,
                    error = false,
                    loading = false
                )
            } else {
                _state.value = _state.value.copy(
                    error = true,
                    loading = false
                )
            }

        }
    }

    private fun updateCityNameDataStore(cityName: String) {
        viewModelScope.launch {
            val responseDataStore = viewModelScope.async(Dispatchers.IO) {
                return@async saveCityNameToDataStoreUseCase.invoke(cityName)
            }

            responseDataStore.await()
        }
    }

    private fun updateCityCordDataStore(lat: Double, lon: Double) {
        viewModelScope.launch {
            val responseDataStore = viewModelScope.async(Dispatchers.IO) {
                return@async saveCordToDataStoreUseCase.invoke(lat, lon)
            }

            responseDataStore.await()
        }
    }

}