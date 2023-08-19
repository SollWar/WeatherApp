package ru.nikita.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
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
                    val mainScreenViewModel = hiltViewModel<MainScreenViewModel>()

                    // Пред загрузка прогноза во время Splash экрана
                    installSplashScreen().apply {
                        setKeepOnScreenCondition {
                            !mainScreenViewModel.displayed.value
                        }
                    }

                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = "main") {
                        composable(route = "main") {
                            val mainScreenState = mainScreenViewModel.state.collectAsState()
                            when (mainScreenState.value) {
                                is MainScreenState.Display -> {
                                    MainScreenDisplay(
                                        onState = mainScreenViewModel::onState,
                                        onSearchClick = { navController.navigate(route = "search") },
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
                        composable(route = "search") {
                            val searchScreenViewModel = hiltViewModel<SearchScreenViewModel>()
                            val state = searchScreenViewModel.state.collectAsState()
                            SearchScreen(
                                state = state.value,
                                onEvent = searchScreenViewModel::onEvent
                            ) { navController.popBackStack() }
                        }
                    }
                }
            }
        }
    }
}