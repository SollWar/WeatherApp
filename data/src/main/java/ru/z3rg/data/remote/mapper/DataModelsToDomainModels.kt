package ru.z3rg.data.remote.mapper

import ru.z3rg.data.remote.models.Forecastday
import ru.z3rg.data.remote.models.WeatherApiResponse
import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.models.ForecastWeatherDay


fun forecastdayToForecastWeatherDay(forecastday: Forecastday): ForecastWeatherDay {
    return ForecastWeatherDay(
        conditionText = forecastday.day?.condition?.text!!,
        conditionIcon = forecastday.day?.condition?.icon!!,
        avgTempC = forecastday.day?.avgtempC!!,
        maxWindKph = forecastday.day?.maxwindKph!!,
        avgHumidity = forecastday.day?.avghumidity!!,
        chanceRain = forecastday.day?.dailyChanceOfRain!!
    )
}

fun weatherApiResponseToForecastWeather(weatherApiResponse: WeatherApiResponse): ForecastWeather {
    val forecastWeather = ForecastWeather(cityName = weatherApiResponse.location?.name!!)
    repeat(weatherApiResponse.forecast?.forecastday?.size!!) {
        forecastWeather.addForecastDay(
            forecastdayToForecastWeatherDay(
                weatherApiResponse.forecast!!.forecastday[it]
            )
        )
    }
    return forecastWeather
}
