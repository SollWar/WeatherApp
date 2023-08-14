package ru.nikita.weatherapp.ui.screens.main.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.nikita.weatherapp.ui.base.EventHandler
import ru.nikita.weatherapp.ui.screens.main.models.MainScreeViewState
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenEvent
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor() : ViewModel(), EventHandler<MainScreenEvent> {

    private val _mainScreenViewState: MutableStateFlow<MainScreeViewState> =
        MutableStateFlow(MainScreeViewState.Loading)
    val mainScreenViewState = _mainScreenViewState.asStateFlow()

    private val _data: MutableStateFlow<String> = MutableStateFlow("")

    private var successLoadData = false

    override fun obtainEvent(event: MainScreenEvent) {
        when (val currentState = _mainScreenViewState.value) {
            is MainScreeViewState.Loading -> reduce(event, currentState)
            is MainScreeViewState.Error -> reduce(event, currentState)
            is MainScreeViewState.Display -> reduce(event, currentState)
        }
    }

    private fun reduce(event: MainScreenEvent, currentState: MainScreeViewState.Loading) {
        if (event is MainScreenEvent.EnterScreen) {
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
            Log.d("ViewModel", "MainScreeViewState.Display")
        }
    }

    private fun errorLoadingData() {
        _mainScreenViewState.value = MainScreeViewState.Error
        successLoadData = !successLoadData
    }

    private fun reloadingData() {
        loadingData()
        _mainScreenViewState.value = MainScreeViewState.Loading
    }

    private fun loadingData() {
        viewModelScope.launch {
            val loadingResponse = viewModelScope.async(Dispatchers.IO) {
                delay(1500)
                return@async "Балашиха"
            }

            _data.value = loadingResponse.await()

            if (successLoadData) {
                showData()
            } else {
                errorLoadingData()
            }
        }
    }

    private fun showData() {
        _mainScreenViewState.value = MainScreeViewState.Display(_data.value)
    }
}