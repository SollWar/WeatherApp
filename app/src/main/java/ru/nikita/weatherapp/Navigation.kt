package ru.nikita.weatherapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import ru.nikita.weatherapp.ui.screens.main.MainScreenDisplay
import ru.nikita.weatherapp.ui.screens.main.MainScreenError
import ru.nikita.weatherapp.ui.screens.main.MainScreenLoading
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenEvent
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenState
import ru.nikita.weatherapp.ui.screens.main.viewmodel.MainScreenViewModel
import ru.nikita.weatherapp.ui.screens.search.SearchScreen
import ru.nikita.weatherapp.ui.screens.search.viewmodel.SearchScreenViewModel

@Composable
fun Navigation(
    mainScreenViewModel: MainScreenViewModel
) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route) {
            val mainScreenState = mainScreenViewModel.state.collectAsState()
            when (mainScreenState.value) {
                is MainScreenState.Display -> {
                    MainScreenDisplay(
                        onState = mainScreenViewModel::onState,
                        onSearchClick = { navController.navigate(Screen.SearchScreen.route) },
                        state = mainScreenState.value as MainScreenState.Display
                    )
                }
                is MainScreenState.Error -> {
                    MainScreenError(
                        onState = mainScreenViewModel::onState,
                        onReloadClick = mainScreenViewModel::onEvent
                    )
                }
                is MainScreenState.Loading -> {
                    MainScreenLoading(
                        onState = mainScreenViewModel::onState
                    )
                }
            }
        }
        composable(route = Screen.SearchScreen.route) {
            val searchScreenViewModel: SearchScreenViewModel = hiltViewModel()
            val state = searchScreenViewModel.state.collectAsState()
            SearchScreen(
                state = state.value,
                onEvent = searchScreenViewModel::onEvent
            ) {
                navController.popBackStack()
                mainScreenViewModel.onEvent(MainScreenEvent.ReloadForecast)
            }
        }
    }
}