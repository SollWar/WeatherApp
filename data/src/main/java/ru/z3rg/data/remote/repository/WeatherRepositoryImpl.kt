package ru.z3rg.data.remote.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.z3rg.data.remote.mapper.weatherApiResponseToForecastWeather
import ru.z3rg.data.remote.retrofit.WeatherApi
import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.repository.WeatherRepository
import javax.inject.Inject


class WeatherRepositoryImpl @Inject constructor (
    private val weatherApi: WeatherApi
): WeatherRepository {

    override suspend fun getForecast(cityName: String): ForecastWeather = withContext(Dispatchers.IO) {
        val request = weatherApi.getWeather(city = cityName)
        return@withContext weatherApiResponseToForecastWeather(request.body()!!)
    }
}