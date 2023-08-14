package ru.z3rg.domain.repository

import ru.z3rg.domain.models.ForecastWeather

interface WeatherRepository {

    suspend fun getForecast(cityName: String): ForecastWeather

}