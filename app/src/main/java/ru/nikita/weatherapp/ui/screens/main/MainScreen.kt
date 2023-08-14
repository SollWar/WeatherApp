package ru.nikita.weatherapp.ui.screens.main

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import ru.nikita.weatherapp.ui.screens.main.models.MainScreeViewState
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenEvent
import ru.nikita.weatherapp.ui.screens.main.viewmodel.MainScreenViewModel

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val viewState = viewModel.mainScreenViewState.collectAsState()

    when (val state = viewState.value) {
        is MainScreeViewState.Start -> MainScreenLoading()
        is MainScreeViewState.Loading -> MainScreenLoading(state.cityName, viewModel.currentDate)
        is MainScreeViewState.Error -> MainScreenError {
            viewModel.obtainEvent(MainScreenEvent.ReloadedData)
        }

        is MainScreeViewState.Display -> MainScreenLoaded(state.forecast, viewModel.currentDate)
    }

    Log.d("ViewState", viewState.value.toString())

    LaunchedEffect(key1 = viewState, block = {
        viewModel.obtainEvent(event = MainScreenEvent.EnterScreen)
    })
}