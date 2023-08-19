package ru.nikita.weatherapp.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.nikita.weatherapp.R
import ru.nikita.weatherapp.ui.screens.main.models.MainScreenState
import ru.nikita.weatherapp.ui.theme.*
import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.models.ForecastWeatherDay
import java.text.SimpleDateFormat
import java.util.*

@Preview(device = "spec:width=360dp,height=800dp,dpi=440", showSystemUi = true)
@Composable
fun MainScreenDisplayPreview() {
    MaterialTheme(
        colorScheme = DarkColors
    ) {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            MainScreenDisplay(state = MainScreenState.Display(
                currentDate = "11 ноября, понедельник",
                forecast = ForecastWeather(
                    cityName = "Балашиха",
                    forecastDay = arrayListOf(
                        ForecastWeatherDay(),
                        ForecastWeatherDay(),
                        ForecastWeatherDay(),
                        ForecastWeatherDay(),
                        ForecastWeatherDay()
                    ),
                    forecastCurrent = ForecastWeatherDay()
                )
            ))
        }
    }
}

@Composable
fun MainScreenDisplay(
    onState: (MainScreenState) -> Unit = {},
    onSearchClick: () -> Unit = {},
    state: MainScreenState.Display
) {

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
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
                        text = state.forecast.cityName,
                        style = juraFont24sp(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Text(
                        text = state.currentDate,
                        style = juraFont20sp(
                            color = MaterialTheme.colorScheme.onPrimary
                        )
                    )
                }
                Box(
                    modifier = Modifier
                        .height(40.dp)
                        .width(40.dp)
                        .clip(RoundedCornerShape(10.dp))
                        .background(MaterialTheme.colorScheme.primaryContainer)
                        .clickable {
                            onSearchClick()
                        },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        Icons.Default.Search,
                        contentDescription = "Поиск города",
                        tint = MaterialTheme.colorScheme.primary
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
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text =
                            state.forecast.forecastCurrent.avgTempC.toInt().toString() + "°",
                            style = juraFont64sp(
                                color = MaterialTheme.colorScheme.primary
                            )
                        )
                        Icon(
                            rememberAsyncImagePainter(
                                model = "https:" + state.forecast.forecastDay[0].conditionIcon
                            ),
                            "Погода",
                            Modifier
                                .width(64.dp)
                                .height(64.dp),
                            tint = MaterialTheme.colorScheme.primary
                        )
                    }
                    Text(
                        text = state.forecast.forecastCurrent.conditionText,
                        style = juraFont20sp(
                            color = MaterialTheme.colorScheme.primary
                        )
                    )
                    Row(
                        modifier = Modifier
                            .padding(top = 8.dp, bottom = 8.dp)
                            .fillMaxWidth()
                            .height(72.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(MaterialTheme.colorScheme.primaryContainer),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        WeatherIndicators(
                            stringResource(R.string.wind),
                            (state.forecast.forecastCurrent.maxWindKph / 3.6).toInt().toString()
                                    + " " + stringResource(R.string.wind_speed),
                            R.drawable.wind
                        )
                        WeatherIndicators(
                            stringResource(R.string.humidity),
                            state.forecast.forecastCurrent.avgHumidity.toInt().toString() + " %",
                            R.drawable.humidity
                        )
                        WeatherIndicators(
                            stringResource(R.string.rain),
                            state.forecast.forecastDay[0].chanceRain.toInt().toString() + " %",
                            R.drawable.rain
                        )
                    }
                }
                for (i in 1..4) {
                    WeatherDay(
                        state.forecast.forecastDay[i]
                    )
                }
            }
        }
        Spacer(
            modifier = Modifier
                .height(16.dp)
        )
    }

    LaunchedEffect(key1 = "", block = {
        onState(MainScreenState.Display())
    })
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
            tint = MaterialTheme.colorScheme.primary
        )
        Text(
            text = value,
            style = juraFont16sp(
                color = MaterialTheme.colorScheme.primary
            )
        )
        Text(
            text = text,
            style = juraFont12sp(
                color = MaterialTheme.colorScheme.onPrimary
            )
        )
    }
}

@Composable
fun WeatherDay(
    forecastDay: ForecastWeatherDay
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
                style = juraFont16sp(
                    color = MaterialTheme.colorScheme.onPrimary
                ),
            )
            Text(
                text = value,
                style = juraFont16sp(
                    textAlign =  TextAlign.End,
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
    }

    val date = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(forecastDay.date)
    val formatter = SimpleDateFormat("dd MMMM, EEEE", Locale.getDefault())


    Box(
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Text(
                text = formatter.format(date!!),
                style = juraFont16sp(
                    color = MaterialTheme.colorScheme.primary
                )
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
                        stringResource(R.string.temperature),
                        forecastDay.minTempC.toInt().toString() + "°" + " - "
                                + forecastDay.maxTempC.toInt().toString() + "°"
                    )
                    Indicator(
                        stringResource(R.string.wind),
                        (forecastDay.maxWindKph / 3.6).toInt().toString()
                                + " " + stringResource(R.string.wind_speed)
                    )
                    Indicator(
                        stringResource(R.string.humidity),
                        forecastDay.avgHumidity.toInt().toString() + " %"
                    )
                    Indicator(
                        stringResource(R.string.rain),
                        forecastDay.chanceRain.toInt().toString() + " %"
                    )
                }
                Icon(
                    rememberAsyncImagePainter(
                        model = "https:" + forecastDay.conditionIcon
                    ),
                    "Погода",
                    Modifier
                        .width(48.dp)
                        .height(48.dp)
                        .weight(0.2f),
                    tint = MaterialTheme.colorScheme.primary
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = forecastDay.conditionText,
                style = juraFont16sp(
                    textAlign =  TextAlign.End,
                    color = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    }
}