package ru.nikita.weatherapp.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikita.weatherapp.R
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenEvent
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenState
import ru.nikita.weatherapp.ui.theme.juraFont24sp

@Preview
@Composable
fun MainScreenError(
    onState: (MainScreenState) -> Unit = {},
    onReloadClick: (MainScreenEvent) -> Unit = {}
) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.error_forecast),
            style = juraFont24sp(
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Box(
            modifier = Modifier
                .padding(top = 16.dp)
                .width(200.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(
                    MaterialTheme.colorScheme.primaryContainer
                )
                .clickable {
                    onReloadClick(MainScreenEvent.ReloadForecast)
                },
        ){
            Text(
                modifier = Modifier
                    .fillMaxSize(),
                text = "Повторить",
                style = juraFont24sp(
                    textAlign = TextAlign.Center,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }

    LaunchedEffect(key1 = "", block = {
        onState(MainScreenState.Error)
    })
}