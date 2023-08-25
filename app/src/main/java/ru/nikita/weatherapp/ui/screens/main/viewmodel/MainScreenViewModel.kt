package ru.nikita.weatherapp.ui.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _displayed: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val displayed = _displayed.asStateFlow()

    private val _currentDate = Calendar.getInstance().time
    private val formatter = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())
    private val currentDate = formatter.format(_currentDate)

    fun onState(mainScreenState: MainScreenState) {
        when (mainScreenState) {
            is MainScreenState.Display -> {
                _displayed.value = true
            }

            is MainScreenState.Error -> {
            }

            is MainScreenState.Loading -> {
                loadForecast()
            }
        }
    }

    init {
        timeLimitPreLoad()
    }

    // Ограничение времени отображения Splash Screen
    private fun timeLimitPreLoad() = viewModelScope.launch {
        delay(600)
        _displayed.value = true
    }

    fun onEvent(mainScreenEvent: MainScreenEvent) {
        when (mainScreenEvent) {
            is MainScreenEvent.ReloadForecast -> {
                _state.value = MainScreenState.Loading
                loadForecast()
            }
        }
    }

    private fun loadForecast() {
        viewModelScope.launch(Dispatchers.IO) {
            val cityName = getCityNameFromDataStoreUseCase.invoke()
            val cityCord = getCordForDataStoreUseCase.invoke()
            val forecast = getForecastForCityNameUseCase.invoke(cityCord)

            val forecastBody = forecast.body
            if (forecastBody != null) {
                val updatedForecast = forecastBody.copy(
                    cityName = cityName,
                    cityCord = cityCord
                )
                withContext(Dispatchers.Main) {
                    _state.value = MainScreenState.Display(
                        forecast = updatedForecast,
                        currentDate = currentDate
                    )
                }
            } else {
                withContext(Dispatchers.Main) {
                    _state.value = MainScreenState.Error
                }
            }
        }
    }
}