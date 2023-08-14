package ru.nikita.weatherapp.ui.screens.main.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nikita.weatherapp.ui.base.EventHandler
import ru.nikita.weatherapp.ui.screens.main.models.MainScreeViewState
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenEvent
import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.usecases.GetForecastForCityNameFromDataStoreUseCase
import ru.z3rg.domain.usecases.GetForecastForCityNameUseCase
import ru.z3rg.domain.usecases.SaveCityNameToDataStoreUseCase
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getForecastForCityNameUseCase: GetForecastForCityNameUseCase,
    private val getForecastForCityNameFromDataStoreUseCase: GetForecastForCityNameFromDataStoreUseCase,
    private val saveCityNameToDataStoreUseCase: SaveCityNameToDataStoreUseCase
) : ViewModel(), EventHandler<MainScreenEvent> {

    private val _mainScreenViewState: MutableStateFlow<MainScreeViewState> =
        MutableStateFlow(MainScreeViewState.Start)
    val mainScreenViewState = _mainScreenViewState.asStateFlow()

    private val _data: MutableStateFlow<ForecastWeather> = MutableStateFlow(ForecastWeather())
    private val _cityName: MutableStateFlow<String> = MutableStateFlow("")

    private val _currentDate = Calendar.getInstance().time
    private val formatter = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())
    val currentDate: String = formatter.format(_currentDate)

    private var successLoadData = true

    override fun obtainEvent(event: MainScreenEvent) {
        when (val currentState = _mainScreenViewState.value) {
            is MainScreeViewState.Start -> reduce(event, currentState)
            is MainScreeViewState.Loading -> reduce(event, currentState)
            is MainScreeViewState.Error -> reduce(event, currentState)
            is MainScreeViewState.Display -> reduce(event, currentState)
        }
    }

    private fun reduce(event: MainScreenEvent, currentState: MainScreeViewState.Start) {
        if (event is MainScreenEvent.EnterScreen) {
            loadingCity()
        }
    }

    private fun reduce(event: MainScreenEvent, currentState: MainScreeViewState.Loading) {
        if (event is MainScreenEvent.LoadingData) {
            loadingData()
        }
    }

    private fun reduce(event: MainScreenEvent, currentState: MainScreeViewState.Error) {
        when (event) {
            MainScreenEvent.ReloadedData -> reloadingData()
            else -> {}
        }
    }

    private fun reduce(event: MainScreenEvent, currentState: MainScreeViewState.Display) {
        if (event is MainScreenEvent.LoadedData) {
            showData()
        }
    }

    private fun errorLoadingData() {
        _mainScreenViewState.value = MainScreeViewState.Error
        successLoadData = !successLoadData
    }

    private fun reloadingData() {
        loadingData()
        _mainScreenViewState.value = MainScreeViewState.Loading(_cityName.value)
    }

    private fun loadingData() {
        viewModelScope.launch {
            val loadingResponse = viewModelScope.async(Dispatchers.IO) {
                return@async getForecastForCityNameUseCase.invoke(_cityName.value)
            }

            _data.value = loadingResponse.await()
            showData()
        }
    }

    private fun loadingCity() {
        viewModelScope.launch {
            val loadingResponse = viewModelScope.async(Dispatchers.IO) {
                return@async getForecastForCityNameFromDataStoreUseCase.invoke()
            }
            //saveCityNameToDataStoreUseCase.invoke("Унеча")
            _cityName.value = loadingResponse.await()
            showLoading()
        }
    }

    private fun showLoading() {
        _mainScreenViewState.value = MainScreeViewState.Loading(_cityName.value)
        this.obtainEvent(MainScreenEvent.LoadingData)
    }

    private fun showData() {
        _mainScreenViewState.value = MainScreeViewState.Display(_data.value)
    }
}