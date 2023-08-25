package ru.z3rg.data.remote.mapper

import ru.z3rg.data.remote.models.*
import ru.z3rg.domain.models.*


fun forecastdayToForecastWeatherDay(forecastday: Forecastday): ForecastWeatherDay {
    val forecastWeatherDay = ForecastWeatherDay(
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
    forecastday.hour.forEach {
        forecastWeatherDay.addForecastHour(
            hourToForecastHours(it)
        )
    }
    return forecastWeatherDay
}

fun weatherApiResponseToForecastWeather(weatherApiResponse: WeatherApiResponse): ForecastWeather {
    val forecastWeather = ForecastWeather(
        cityName = weatherApiResponse.location.name,
        forecastCurrent = ForecastWeatherDay(
            conditionText = weatherApiResponse.current.condition.text,
            conditionIcon = weatherApiResponse.current.condition.icon,
            avgTempC = weatherApiResponse.current.temp_c,
            maxWindKph = weatherApiResponse.current.wind_kph,
            avgHumidity = weatherApiResponse.current.humidity
        )
    )
    weatherApiResponse.forecast.forecastday.forEach {
        forecastWeather.addForecastDay(
            forecastdayToForecastWeatherDay(it)
        )
    }
    return forecastWeather
}

fun citySearchToCityList(citySearch: CitySearch): CityList {
    val cityList = CityList()
    citySearch.forEach {
        cityList.addCityEntity(
            citySearchItemToCityEntity(it)
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

fun hourToForecastHours(hour: Hour): ForecastHours {
    return ForecastHours(
        time = hour.time,
        tempC = hour.tempC,
        conditionIcon = hour.condition.icon,
        conditionText = hour.condition.text,
        windKhp = hour.windKhp,
        humidity = hour.humidity,
        chanceOfRain = hour.chanceOfRain
    )
}