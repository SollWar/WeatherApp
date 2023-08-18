package ru.nikita.weatherapp.ui.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenEvent
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenState
import ru.z3rg.domain.usecases.GetCordForDataStoreUseCase
import ru.z3rg.domain.usecases.GetForecastForCityNameFromDataStoreUseCase
import ru.z3rg.domain.usecases.GetForecastForCityNameUseCase
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCityNameFromDataStoreUseCase: GetForecastForCityNameFromDataStoreUseCase,
    private val getForecastForCityNameUseCase: GetForecastForCityNameUseCase,
    private val getCordForDataStoreUseCase: GetCordForDataStoreUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState.Loading)
    val state = _state.asStateFlow()
    private val _currentDate = Calendar.getInstance().time
    private val formatter = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())
    private val currentDate = formatter.format(_currentDate)

    fun onState(mainScreenState: MainScreenState) {
        when (mainScreenState) {
            is MainScreenState.Display -> {
                isCityChange()
            }
            is MainScreenState.Error -> {
                
            }
            is MainScreenState.Loading -> {
                loadForecast()
            }
        }
    }

    fun onEvent(mainScreenEvent: MainScreenEvent) {
        when (mainScreenEvent) {
            is MainScreenEvent.ReloadForecast -> {
                _state.value = MainScreenState.Loading
                loadForecast()
            }
        }
    }
    
    private fun isCityChange() {
        viewModelScope.launch {
            val responseDataStore = viewModelScope.async(Dispatchers.IO) {
                return@async getCordForDataStoreUseCase.invoke()
            }

            if ((_state.value as MainScreenState.Display).forecast.cityCord != responseDataStore.await()) {
                loadForecast()
            }
        }
    }

    private fun loadForecast() {
        viewModelScope.launch {
            val responseCityName = viewModelScope.async(Dispatchers.IO) {
                return@async getCityNameFromDataStoreUseCase.invoke()
            }
            
            val responseCityCord = viewModelScope.async(Dispatchers.IO) {
                return@async getCordForDataStoreUseCase.invoke()
            }

            val responseForecast = viewModelScope.async(Dispatchers.IO) {
                return@async getForecastForCityNameUseCase.invoke(responseCityCord.await())
            }
            
            if (responseForecast.await().error == null) {
                _state.value = MainScreenState.Display(
                    forecast = responseForecast.await().copy(
                        cityName = responseCityName.await(),
                        cityCord = responseCityCord.await()
                    ),
                    currentDate = currentDate,
                    loading = false
                )
            } else {
                _state.value = MainScreenState.Error
            }

        }
    }
}