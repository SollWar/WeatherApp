package ru.z3rg.domain.repository

import ru.z3rg.domain.models.CityList
import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.models.Result

interface WeatherRepository {

    suspend fun getForecast(
        cityName: String,
        lang: String
    ): Result<ForecastWeather>
    suspend fun searchCity(cityName: String): Result<CityList>

}