package ru.nikita.weatherapp.ui.screens.main

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import ru.nikita.weatherapp.R
import ru.nikita.weatherapp.ui.screens.main.models.MainScreeViewState
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenEvent
import ru.nikita.weatherapp.ui.screens.main.viewmodel.MainScreenViewModel
import ru.nikita.weatherapp.ui.theme.DarkBackground
import ru.nikita.weatherapp.ui.theme.DarkForeground
import ru.nikita.weatherapp.ui.theme.White
import ru.nikita.weatherapp.ui.theme.juraFont12spGray
import ru.nikita.weatherapp.ui.theme.juraFont16sp
import ru.nikita.weatherapp.ui.theme.juraFont16spGray
import ru.nikita.weatherapp.ui.theme.juraFont20spGray
import ru.nikita.weatherapp.ui.theme.juraFont24sp
import ru.nikita.weatherapp.ui.theme.juraFont64sp

@Composable
fun MainScreen(
    viewModel: MainScreenViewModel = hiltViewModel()
) {

    val viewState = viewModel.mainScreenViewState.collectAsState()

    when (val state = viewState.value) {
        MainScreeViewState.Loading -> MainScreenLoaded(loading = true)
        MainScreeViewState.Error -> MainScreenError {
            viewModel.obtainEvent(MainScreenEvent.ReloadedData)
        }

        is MainScreeViewState.Display -> MainScreenLoaded(state.cityName, false)
    }

    Log.d("ViewState", viewState.value.toString())

    LaunchedEffect(key1 = viewState, block = {
        viewModel.obtainEvent(event = MainScreenEvent.EnterScreen)
    })
}