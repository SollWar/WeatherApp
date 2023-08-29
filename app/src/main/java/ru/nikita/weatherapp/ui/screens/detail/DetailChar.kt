package ru.nikita.weatherapp.ui.screens.detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikita.weatherapp.R
import ru.nikita.weatherapp.ui.theme.juraFont12sp
import ru.nikita.weatherapp.ui.theme.juraFont8sp

data class Hour(
    val time: String,
    val icon: Int,
    val temp: Int,
    val description: String
)

@Preview(device = "spec:width=411dp,height=891dp")
@Composable
fun DetailChar() {
    val hoursForecast = arrayListOf(
        Hour(
            time = "00:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 10,
            description = "Пасмурно"
        ),
        Hour(
            time = "01:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 14,
            description = "Пасмурно"
        ),
        Hour(
            time = "02:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 25,
            description = "Пасмурно"
        ),
        Hour(
            time = "03:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 19,
            description = "Пасмурно"
        ),
        Hour(
            time = "04:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 20,
            description = "Пасмурно"
        ),
        Hour(
            time = "05:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 24,
            description = "Пасмурно"
        ),
        Hour(
            time = "06:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 29,
            description = "Пасмурно"
        ),
        Hour(
            time = "07:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 27,
            description = "Пасмурно"
        ),
        Hour(
            time = "09:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 25,
            description = "Пасмурно"
        ),
        Hour(
            time = "10:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 20,
            description = "Пасмурно"
        ),
        Hour(
            time = "11:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 15,
            description = "Пасмурно"
        ),
        Hour(
            time = "12:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 12,
            description = "Пасмурно"
        ),
        Hour(
            time = "13:00",
            icon = R.drawable.weather_ico_placeholder,
            temp = 5,
            description = "Пасмурно"
        )
    )
    Column {
        LazyRow(
            modifier = Modifier
                .clip(RoundedCornerShape(10.dp))
                .background(Color.Black),
            content = {
            items(hoursForecast) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = it.time,
                        style = juraFont12sp(color = Color.White)
                    )
                    Image(
                        painter = painterResource(id = it.icon),
                        contentDescription = it.description
                    )
                    Text(
                        text = it.temp.toString() + "°",
                        style = juraFont12sp(color = Color.White)
                    )
                    Text(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .width(41.dp),
                        text = it.description,
                        style = juraFont8sp(color = Color.White)
                    )
                }
            }
        })
    }
}