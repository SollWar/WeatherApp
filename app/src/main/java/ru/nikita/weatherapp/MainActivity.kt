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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import dagger.hilt.android.AndroidEntryPoint
import ru.nikita.weatherapp.ui.screens.main.MainScreenDisplay
import ru.nikita.weatherapp.ui.screens.main.MainScreenError
import ru.nikita.weatherapp.ui.screens.main.MainScreenLoading
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenState
import ru.nikita.weatherapp.ui.screens.main.viewmodel.MainScreenViewModel
import ru.nikita.weatherapp.ui.screens.search.SearchScreen
import ru.nikita.weatherapp.ui.screens.search.viewmodel.SearchScreenViewModel
import ru.nikita.weatherapp.ui.theme.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AppTheme {
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
                            when (state.value) {
                                is MainScreenState.Loading -> {
                                    MainScreenLoading(
                                        onState = viewModel::onState
                                    )
                                }

                                is MainScreenState.Error -> {
                                    MainScreenError(
                                        onState = viewModel::onState,
                                        onReloadClick = viewModel::onEvent
                                    )
                                }

                                is MainScreenState.Display -> {
                                    MainScreenDisplay(
                                        onState = viewModel::onState,
                                        onSearchClick =  { navController.navigate(route = "search") },
                                        state = state.value as MainScreenState.Display
                                    )
                                }
                            }
                        }
                        composable(route = "search") {
                            val viewModel = hiltViewModel<SearchScreenViewModel>()
                            val state = viewModel.state.collectAsState()
                            SearchScreen(
                                state = state.value,
                                onEvent = viewModel::onEvent
                            ) { navController.popBackStack() }
                        }
                    }
                }
            }
        }
    }
}