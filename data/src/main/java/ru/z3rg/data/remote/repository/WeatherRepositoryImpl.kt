package ru.z3rg.data.remote.repository

import ru.z3rg.data.remote.mapper.citySearchToCityList
import ru.z3rg.data.remote.mapper.weatherApiResponseToForecastWeather
import ru.z3rg.data.remote.retrofit.WeatherApi
import ru.z3rg.domain.models.CityList
import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.models.Result
import ru.z3rg.domain.repository.WeatherRepository
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor (
    private val weatherApi: WeatherApi
): WeatherRepository {

    override suspend fun getForecast(cityName: String, lang: String): Result<ForecastWeather> {
        return try {
            val request = weatherApi.getWeather(
                city = cityName,
                lang = lang
            )
            Result(
                body = weatherApiResponseToForecastWeather(request.body()!!),
                errorMessage = null
            )
        } catch (e: Exception) {
            Result(
                body = null,
                errorMessage = e.message.toString()
            )
        }
    }

    override suspend fun searchCity(cityName: String): Result<CityList>   {
        return try {
            val request = weatherApi.searchCity(cityName = cityName)
            Result(
                body = citySearchToCityList(request.body()!!),
                errorMessage = null
            )
        } catch (e: Exception) {
            Result(
                body = null,
                errorMessage = e.message.toString()
            )
        }

    }
}