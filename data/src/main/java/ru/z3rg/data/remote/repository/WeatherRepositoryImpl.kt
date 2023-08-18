package ru.z3rg.data.remote.repository

import ru.z3rg.data.remote.mapper.citySearchToCityList
import ru.z3rg.data.remote.mapper.weatherApiResponseToForecastWeather
import ru.z3rg.data.remote.retrofit.WeatherApi
import ru.z3rg.domain.models.CityList
import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.repository.WeatherRepository
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor (
    private val weatherApi: WeatherApi
): WeatherRepository {

    override suspend fun getForecast(cityName: String): ForecastWeather {
        return try {
            val request = weatherApi.getWeather(city = cityName)
            weatherApiResponseToForecastWeather(request.body()!!)
        } catch (e: Exception) {
            ForecastWeather(
                error = "Error"
            )
        }
    }

    override suspend fun searchCity(cityName: String): CityList   {
        return try {
            val request = weatherApi.searchCity(cityName = cityName)
            citySearchToCityList(request.body()!!)
        } catch (e: Exception) {
            CityList(
                error = "Error"
            )
        }

    }
}