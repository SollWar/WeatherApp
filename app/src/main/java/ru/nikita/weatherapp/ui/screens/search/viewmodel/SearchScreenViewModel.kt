package ru.nikita.weatherapp.ui.screens.search.viewmodel

import android.util.Log
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
import javax.inject.Inject

@HiltViewModel
class SearchScreenViewModel @Inject constructor(
    private val findCityByCityNameUseCase: FindCityByCityNameUseCase,
    private val saveCityNameToDataStoreUseCase: SaveCityNameToDataStoreUseCase
): ViewModel() {

    private val _state: MutableStateFlow<SearchScreenState> = MutableStateFlow(SearchScreenState())
    val state = _state.asStateFlow()

    fun onEvent(searchScreenEvent: SearchScreenEvent) {
        when (searchScreenEvent) {
            is SearchScreenEvent.UpdateTextField -> {
                updateTextField(searchScreenEvent.textFieldValue)
                Log.d("SearchScreenViewModel", searchScreenEvent.textFieldValue)
            }
            is SearchScreenEvent.UpdateCityList -> {
                if (_state.value.textFieldValue != "") {
                    updateCityList()
                }
            }
            is SearchScreenEvent.UpdateCityNameDataStore -> {
                updateCityNameDataStore(searchScreenEvent.cityName)
            }
        }
    }

    private fun updateTextField(textFieldValue: String) {
        _state.value = _state.value.copy(
            textFieldValue = textFieldValue
        )
        Log.d("SearchScreenViewModel", state.value.textFieldValue)
    }

    private fun updateCityList() {
        viewModelScope.launch {
            val responseCityList= viewModelScope.async(Dispatchers.IO) {
                return@async findCityByCityNameUseCase.invoke(_state.value.textFieldValue)
            }

            _state.value = _state.value.copy(
                cityList = responseCityList.await()
            )
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

}