package ru.z3rg.domain.repository

import ru.z3rg.domain.models.CityList
import ru.z3rg.domain.models.ForecastWeather

interface WeatherRepository {

    suspend fun getForecast(
        cityName: String,
        lang: String
    ): ForecastWeather
    suspend fun searchCity(cityName: String): CityList

}