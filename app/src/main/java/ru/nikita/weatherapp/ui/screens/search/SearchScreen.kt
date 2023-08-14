package ru.nikita.weatherapp.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikita.weatherapp.ui.theme.DarkBackground
import ru.nikita.weatherapp.ui.theme.DarkForeground
import ru.nikita.weatherapp.ui.theme.White
import ru.nikita.weatherapp.ui.theme.juraFont12spGray
import ru.nikita.weatherapp.ui.theme.juraFont16sp
import ru.nikita.weatherapp.ui.theme.juraFont16spGray
import ru.nikita.weatherapp.ui.theme.juraFont20spGray
import ru.nikita.weatherapp.ui.theme.juraFont24sp

@Preview
@Composable
fun SearchScreen() {

    val city = listOf("Балашиха", "Квашиха", "Увашиха")

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(DarkBackground)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .fillMaxWidth()
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth(),
                value = "Балашиха",
                onValueChange = {

                },
                textStyle = juraFont24sp(),
                trailingIcon = {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Поиск города",
                        tint = White
                    )
                },
                label = {
                    Text(
                        text = "Название города",
                        style = juraFont16spGray()
                    )
                }
            )
        }
        LazyColumn(content = {
            items(city) {
                CityItem(name = it)
            }
        })
    }
}

@Composable
fun CityItem(
    name: String
){
    Box(
        modifier = Modifier
            .padding(top = 4.dp, start = 32.dp, end = 16.dp)
            .height(60.dp)
            .fillMaxWidth(),
        contentAlignment = Alignment.CenterStart
    ) {
        Text(
            text = name,
            style = juraFont24sp()
        )
    }
}