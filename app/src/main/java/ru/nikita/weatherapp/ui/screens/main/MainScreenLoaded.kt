package ru.nikita.weatherapp.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import ru.nikita.weatherapp.R
import ru.nikita.weatherapp.ui.theme.*
import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.models.ForecastWeatherDay
import java.text.SimpleDateFormat
import java.util.*

@Preview(device = "spec:width=360dp,height=800dp,dpi=440", showSystemUi = true)
@Composable
fun MainScreenLoadedPreview() {

    MainScreenLoaded(
        ForecastWeather(
            cityName = "Балашиха",
            forecastDay = arrayListOf(
                ForecastWeatherDay(
                    conditionText = "Ветрянка",
                    conditionIcon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                    avgTempC = 10.0,
                    minTempC = 10.0,
                    maxTempC = 20.0,
                    maxWindKph = 10.0,
                    avgHumidity = 10.0,
                    chanceRain = 90.0,
                    date = "2023-08-14"
                ),
                ForecastWeatherDay(
                    conditionText = "Ветрянка",
                    conditionIcon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                    avgTempC = 10.0,
                    minTempC = 10.0,
                    maxTempC = 20.0,
                    maxWindKph = 10.0,
                    avgHumidity = 10.0,
                    chanceRain = 90.0,
                    date = "2023-08-15"
                ),
                ForecastWeatherDay(
                    conditionText = "Ветрянка",
                    conditionIcon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                    avgTempC = 10.0,
                    minTempC = 10.0,
                    maxTempC = 20.0,
                    maxWindKph = 10.0,
                    avgHumidity = 10.0,
                    chanceRain = 90.0,
                    date = "2023-08-16"
                ),
                ForecastWeatherDay(
                    conditionText = "Ветрянка",
                    conditionIcon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                    avgTempC = 10.0,
                    minTempC = 10.0,
                    maxTempC = 20.0,
                    maxWindKph = 10.0,
                    avgHumidity = 10.0,
                    chanceRain = 90.0,
                    date = "2023-08-17"
                ),
                ForecastWeatherDay(
                    conditionText = "Ветрянка",
                    conditionIcon = "//cdn.weatherapi.com/weather/64x64/day/113.png",
                    avgTempC = 10.0,
                    minTempC = 10.0,
                    maxTempC = 20.0,
                    maxWindKph = 10.0,
                    avgHumidity = 10.0,
                    chanceRain = 90.0,
                    date = "2023-08-18"
                )
            )
        ), currentDate = "14 августа, понедельник"
    )
}


@Composable
fun MainScreenLoaded(
    forecast: ForecastWeather,
    currentDate: String
) {


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
                        text = forecast.cityName,
                        style = juraFont24sp()
                    )
                    Text(
                        modifier = Modifier,
                        text = currentDate,
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
                Column {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text =
                            forecast.forecastDay[0].maxTempC.toInt().toString() + "°",
                            style = juraFont64sp()
                        )
                        Icon(
                            rememberAsyncImagePainter(
                                model = "https:" + forecast.forecastDay[0].conditionIcon
                            ),
                            "Погода",
                            Modifier
                                .width(64.dp)
                                .height(64.dp)
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
                            forecast.forecastDay[0].maxWindKph.toInt().toString() + " км/ч",
                            R.drawable.wind
                        )
                        WeatherIndicators(
                            "Влажность",
                            forecast.forecastDay[0].avgHumidity.toInt().toString() + " %",
                            R.drawable.humidity
                        )
                        WeatherIndicators(
                            "Дождь",
                            forecast.forecastDay[0].chanceRain.toInt().toString() + " %",
                            R.drawable.rain
                        )
                    }
                }
                WeatherDay(
                    forecast.forecastDay[1]
                )
                WeatherDay(
                    forecast.forecastDay[2]
                )
                WeatherDay(
                    forecast.forecastDay[3]
                )
                WeatherDay(
                    forecast.forecastDay[4]
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
                style = juraFont16spGray(),
            )
            Text(
                text = value,
                style = juraFont16sp(TextAlign.End)
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
                        forecastDay.minTempC.toInt().toString() + "°" + " - "
                                + forecastDay.maxTempC.toInt().toString() + "°"
                    )
                    Indicator(
                        "Ветер",
                        (forecastDay.maxWindKph / 3.6).toInt().toString() + " м/с"
                    )
                    Indicator(
                        "Влажность",
                        forecastDay.avgHumidity.toInt().toString() + " %"
                    )
                    Indicator(
                        "Дождь",
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
                    tint = White
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = forecastDay.conditionText,
                style = juraFont16spGray(TextAlign.End)
            )
        }
    }
}