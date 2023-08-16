package ru.nikita.weatherapp.ui.screens.search

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikita.weatherapp.ui.screens.search.models.SearchScreenEvent
import ru.nikita.weatherapp.ui.screens.search.models.SearchScreenState
import ru.nikita.weatherapp.ui.theme.White
import ru.nikita.weatherapp.ui.theme.juraFont16spGray
import ru.nikita.weatherapp.ui.theme.juraFont24sp
import ru.z3rg.domain.models.CityEntity
import ru.z3rg.domain.models.CityList

@Preview
@Composable
fun SearchScreenPreview() {
    SearchScreen(
        SearchScreenState(
            cityList = CityList(
                arrayListOf(
                    CityEntity(
                        name = "Город 1",
                        region = "Регион 1 регион",
                        country = "Страна 1 страна",
                        lat = 0.0,
                        lon = 0.0
                    ),
                    CityEntity(
                        name = "Город 2",
                        region = "Регион 2 регион",
                        country = "Страна 2 страна",
                        lat = 0.0,
                        lon = 0.0
                    ),
                    CityEntity(
                        name = "Город 3",
                        region = "Регион 3 регион",
                        country = "Страна 3 страна",
                        lat = 0.0,
                        lon = 0.0
                    )
                )
            )
        )
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
                },
                keyboardOptions = KeyboardOptions(
                    capitalization = KeyboardCapitalization.Words,
                    autoCorrect = true
                ),
                keyboardActions = KeyboardActions(
                    onDone = {
                        onEvent(SearchScreenEvent.UpdateCityList)
                    }
                )
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
    Column(
        modifier = Modifier
            .padding(bottom = 8.dp, start = 32.dp, end = 16.dp)
            .fillMaxWidth()
            .clickable {
                onEvent(SearchScreenEvent.UpdateCityDataStore(
                    cityEntity.name,
                    cityEntity.lat,
                    cityEntity.lon
                ))
                onEnterCity()
            },
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = cityEntity.name,
            style = juraFont24sp()
        )
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