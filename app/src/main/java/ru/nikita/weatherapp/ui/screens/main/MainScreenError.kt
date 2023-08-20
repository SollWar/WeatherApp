package ru.nikita.weatherapp.ui.screens.main

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
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
            modifier = Modifier
                .padding(horizontal = 16.dp),
            text = stringResource(R.string.error_forecast),
            style = juraFont24sp(
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.primary
            )
        )
        Button(
            modifier = Modifier
                .padding(top = 16.dp)
                .width(200.dp)
                .height(60.dp),
            shape = RoundedCornerShape(10.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.primaryContainer
            ),
            onClick = {
                onReloadClick(MainScreenEvent.ReloadForecast)
            }
        ) {
            Text(
                text = stringResource(R.string.repeat),
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