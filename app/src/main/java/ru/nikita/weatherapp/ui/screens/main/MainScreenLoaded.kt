package ru.nikita.weatherapp.ui.screens.main

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

@Preview(device = "spec:width=360dp,height=800dp,dpi=440", showSystemUi = true)
@Composable
fun MainScreenLoadedPreview(){
    MainScreenLoaded(cityName = "Балашиха", false)
}


@Composable
fun MainScreenLoaded(
    cityName: String = "",
    loading: Boolean
) {

    val isLoading = remember{ mutableStateOf(loading) }

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(DarkBackground)
    ) {
        Box(
            modifier = Modifier
                .padding(vertical = 16.dp, horizontal = 16.dp)
                .fillMaxWidth()
                .height(60.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = cityName,
                        style = juraFont24sp()
                    )
                    Text(
                        modifier = Modifier,
                        text = "13 Августа, Воскресенье",
                        style = juraFont20spGray()
                    )
                }
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(DarkForeground),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Поиск города",
                        tint = White
                    )
                }
            }
        }
        Box(
            modifier = Modifier
                .padding(top = 8.dp, start = 16.dp, end = 16.dp)
                .fillMaxWidth()
        ) {
            Column {
                Column(
                    modifier = Modifier
                        .placeholder(
                            visible = isLoading.value,
                            highlight = PlaceholderHighlight.shimmer()
                        )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "18°",
                            style = juraFont64sp()
                        )
                        Icon(
                            painterResource(R.drawable.weather_ico_placeholder),
                            "Погода",
                            Modifier
                                .width(64.dp)
                                .height(64.dp),
                            tint = White
                        )
                    }
                    Text(
                        text = "Переменная облачность",
                        style = juraFont20spGray()
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp)
                            .fillMaxWidth()
                            .height(72.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(DarkForeground),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherIndicators(
                            "Ветер",
                            "10 м/с",
                            R.drawable.wind
                        )
                        WeatherIndicators(
                            "Влажность",
                            "98 %",
                            R.drawable.humidity
                        )
                        WeatherIndicators(
                            "Дождь",
                            "100 %",
                            R.drawable.rain
                        )
                    }
                }
                WeatherDay(
                    "14 августа, понедельник",
                    isLoading.value
                )
                WeatherDay(
                    "15 августа, вторник",
                    isLoading.value
                )
                WeatherDay(
                    "16 августа, среда",
                    isLoading.value
                )
                WeatherDay(
                    "17 августа, четверг",
                    isLoading.value
                )
            }
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
    }
}

@Composable
fun WeatherIndicators(
    text: String,
    value: String,
    icon: Int
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painterResource(icon),
            "Ветер",
            Modifier
                .width(24.dp)
                .height(24.dp),
            tint = White
        )
        Text(
            text = value,
            style = juraFont16sp()
        )
        Text(
            text = text,
            style = juraFont12spGray()
        )
    }
}

@Composable
fun WeatherDay(
    day: String,
    isLoading: Boolean
) {

    @Composable
    fun Indicator(
        text: String,
        value: String
    ) {
        Row(
            Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = text,
                style = juraFont16spGray(),
            )
            Text(
                text = value,
                style = juraFont16sp(TextAlign.End)
            )
        }
    }

    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
            .placeholder(
                visible = isLoading,
                highlight = PlaceholderHighlight.shimmer()
            )
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = day,
                style = juraFont16sp()
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Column(
                    Modifier
                        .padding(end = 16.dp)
                        .weight(1f)
                ) {
                    Indicator(
                        "Температура",
                        "13° - 22°"
                    )
                    Indicator(
                        "Ветер",
                        "10 м/с"
                    )
                    Indicator(
                        "Влажность",
                        "98 %"
                    )
                }
                Icon(
                    painterResource(R.drawable.weather_ico_placeholder),
                    "Погода",
                    Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .weight(0.2f),
                    tint = White
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Переменная облачность",
                style = juraFont16spGray(TextAlign.End)
            )
        }
    }
}