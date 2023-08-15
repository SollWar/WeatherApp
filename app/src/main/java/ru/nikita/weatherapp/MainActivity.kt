package ru.nikita.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nikita.weatherapp.ui.screens.main.MainScreen
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenEvent
import ru.nikita.weatherapp.ui.screens.main.viewmodel.MainScreenViewModel
import ru.nikita.weatherapp.ui.screens.search.SearchScreen
import ru.nikita.weatherapp.ui.screens.search.viewmodel.SearchScreenViewModel
import ru.nikita.weatherapp.ui.theme.WeatherAppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity () {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main") {
                        composable(route = "main") {

                            val viewModel = hiltViewModel<MainScreenViewModel>()
                            val state = viewModel.state.collectAsState()
                            MainScreen(
                                {
                                    navController.navigate(route = "search")
                                },
                                state = state.value
                            )
                            if (it.lifecycle.currentState == Lifecycle.State.RESUMED) {
                                viewModel.onEvent(MainScreenEvent.ReloadForecast)
                            }
                        }
                        composable(route = "search") {
                            val viewModel = hiltViewModel<SearchScreenViewModel>()
                            val state = viewModel.state.collectAsState()
                            SearchScreen(
                                state = state.value,
                                onEvent = viewModel::onEvent
                            ) {
                                navController.popBackStack()
                            }
                        }
                    }
                }
            }
        }
    }
}