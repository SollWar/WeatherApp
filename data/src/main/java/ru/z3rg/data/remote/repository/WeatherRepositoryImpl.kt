package ru.z3rg.data.remote.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
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
        val request = weatherApi.getWeather(city = cityName)
        return weatherApiResponseToForecastWeather(request.body()!!)
    }

    override suspend fun searchCity(cityName: String): CityList = withContext(Dispatchers.IO)  {
        val request = weatherApi.searchCity(cityName = cityName)
        return@withContext citySearchToCityList(request.body()!!)
    }
}