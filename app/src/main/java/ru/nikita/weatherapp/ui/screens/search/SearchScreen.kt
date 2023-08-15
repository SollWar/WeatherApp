package ru.nikita.weatherapp.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikita.weatherapp.ui.screens.search.models.SearchScreenEvent
import ru.nikita.weatherapp.ui.screens.search.models.SearchScreenState
import ru.nikita.weatherapp.ui.theme.*
import ru.z3rg.domain.models.CityEntity

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        SearchScreenState()
    )
}


@Composable
fun SearchScreen(
    state: SearchScreenState,
    onEvent: (SearchScreenEvent) -> Unit = {},
    onEnterCity: () -> Unit = {}
) {

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
                value = state.textFieldValue,
                singleLine = true,
                onValueChange = {
                    onEvent(SearchScreenEvent.UpdateTextField(it))
                    onEvent(SearchScreenEvent.UpdateCityList)
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
            items(state.cityList.cityList) {
                CityItem(
                    it,
                    onEvent,
                    onEnterCity
                )
            }
        })
    }
}

@Composable
fun CityItem(
    cityEntity: CityEntity,
    onEvent: (SearchScreenEvent) -> Unit = {},
    onEnterCity: () -> Unit = {}
) {
    Row(
        modifier = Modifier
            .padding(bottom = 4.dp, start = 32.dp, end = 16.dp)
            .height(60.dp)
            .fillMaxWidth()
            .clickable {
                onEvent(SearchScreenEvent.UpdateCityNameDataStore(cityEntity.name))
                onEnterCity()
            },
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = cityEntity.name,
            style = juraFont24sp()
        )
        Column {
            Text(
                text = cityEntity.region,
                style = juraFont16spGray()
            )
            Text(
                text = cityEntity.country,
                style = juraFont16spGray()
            )
        }
    }

}