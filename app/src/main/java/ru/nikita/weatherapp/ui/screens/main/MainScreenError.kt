package ru.nikita.weatherapp.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikita.weatherapp.ui.theme.DarkForeground
import ru.nikita.weatherapp.ui.theme.juraFont24sp


@Preview
@Composable
fun MainScreenError(
    onClick: () -> Unit = {}
) {
    Column(
        Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 24.dp, horizontal = 16.dp),
            text = "Не удалось загрузить данные",
            style = juraFont24sp(TextAlign.Center)
        )
        Box(
            Modifier
                .width(200.dp)
                .height(60.dp)
                .clip(RoundedCornerShape(10.dp))
                .background(DarkForeground)
                .clickable {
                    onClick()
                },
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Повторить",
                style = juraFont24sp(TextAlign.Center)
            )
        }
    }

}