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
    private val getForecastForCityNameFromDataStoreUseCase: GetForecastForCityNameFromDataStoreUseCase,
    private val getForecastForCityNameUseCase: GetForecastForCityNameUseCase,
    private val getCordForDataStoreUseCase: GetCordForDataStoreUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<MainScreenState> = MutableStateFlow(MainScreenState())
    val state = _state.asStateFlow()
    private val _currentDate = Calendar.getInstance().time
    private val formatter = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())
    private val currentDate = formatter.format(_currentDate)

    fun onEvent(mainScreenEvent: MainScreenEvent) {
        when (mainScreenEvent) {
            is MainScreenEvent.ReloadForecast -> {
                reloadForecast()
            }
        }
    }

    init {
        loadForecast()
    }

    private fun loadForecast() {
        _state.value = _state.value.copy(
            currentDate = currentDate
        )
        _state.value = _state.value.copy(
            loading = true
        )
        viewModelScope.launch {
            val responseCityName = viewModelScope.async(Dispatchers.IO) {
                return@async getForecastForCityNameFromDataStoreUseCase.invoke()
            }

            val responseCityCord = viewModelScope.async(Dispatchers.IO) {
                return@async getCordForDataStoreUseCase.invoke()
            }

            val responseForecast = viewModelScope.async(Dispatchers.IO) {
                return@async getForecastForCityNameUseCase.invoke(responseCityCord.await())
            }

            _state.value = _state.value.copy(
                forecast = responseForecast.await()
            )
            _state.value = _state.value.copy(
                forecast = _state.value.forecast.copy(
                    cityCord = responseCityCord.await(),
                    cityName = responseCityName.await()
                )
            )

            _state.value = _state.value.copy(
                loading = false
            )
        }
    }

    private fun reloadForecast() {
        viewModelScope.launch {
            val responseDataStore = viewModelScope.async(Dispatchers.IO) {
                return@async getCordForDataStoreUseCase.invoke()
            }

            if (_state.value.forecast.cityCord != responseDataStore.await()) {
                loadForecast()
            }
        }
    }

}