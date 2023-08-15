package ru.z3rg.data.remote.mapper

import ru.z3rg.data.remote.models.CitySearch
import ru.z3rg.data.remote.models.CitySearchItem
import ru.z3rg.data.remote.models.Forecastday
import ru.z3rg.data.remote.models.WeatherApiResponse
import ru.z3rg.domain.models.CityEntity
import ru.z3rg.domain.models.CityList
import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.models.ForecastWeatherDay


fun forecastdayToForecastWeatherDay(forecastday: Forecastday): ForecastWeatherDay {
    return ForecastWeatherDay(
        conditionText = forecastday.day.condition.text,
        conditionIcon = forecastday.day.condition.icon,
        avgTempC = forecastday.day.avgtempC,
        minTempC = forecastday.day.mintempC,
        maxTempC = forecastday.day.maxtempC,
        maxWindKph = forecastday.day.maxwindKph,
        avgHumidity = forecastday.day.avghumidity,
        chanceRain = forecastday.day.dailyChanceOfRain,
        date = forecastday.date
    )
}

fun weatherApiResponseToForecastWeather(weatherApiResponse: WeatherApiResponse): ForecastWeather {
    val forecastWeather = ForecastWeather(cityName = weatherApiResponse.location.name)
    repeat(weatherApiResponse.forecast.forecastday.size) {
        forecastWeather.addForecastDay(
            forecastdayToForecastWeatherDay(
                weatherApiResponse.forecast.forecastday[it]
            )
        )
    }
    return forecastWeather
}

fun citySearchToCityList(citySearch: CitySearch): CityList {
    val cityList = CityList()
    repeat(citySearch.size) {
        cityList.addCityEntity(
            citySearchItemToCityEntity(citySearch[it])
        )
    }
    return cityList
}

fun citySearchItemToCityEntity(citySearchItem: CitySearchItem): CityEntity {
    return CityEntity(
        name = citySearchItem.name,
        region = citySearchItem.region,
        country = citySearchItem.country,
        lat = citySearchItem.lat,
        lon = citySearchItem.lon
    )
}