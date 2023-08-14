package ru.nikita.weatherapp.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import ru.nikita.weatherapp.ui.theme.*


@Preview(device = "spec:width=360dp,height=800dp,dpi=440", showSystemUi = true)
@Composable
fun MainScreenLoadingPreview(){
    MainScreenLoading("Балашиха", "13 августа, понедельник")
}

@Composable
fun MainScreenLoading(
    cityName: String = "",
    currentDate: String = ""
) {
    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(DarkBackground),
        horizontalAlignment = Alignment.CenterHorizontally
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

        CircularProgressIndicator(
            modifier = Modifier
                .padding(top = 64.dp)
                .width(60.dp)
                .height(60.dp)
        )
    }
}