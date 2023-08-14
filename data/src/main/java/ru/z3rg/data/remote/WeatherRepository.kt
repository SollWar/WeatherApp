package ru.z3rg.data.remote

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.z3rg.data.remote.models.WeatherApiResponse
import ru.z3rg.data.remote.retrofit.WeatherApi

class WeatherRepository(
    private val weatherApi: WeatherApi
) {
    suspend fun getForecast(): WeatherApiResponse = withContext(Dispatchers.IO) {
        val request = weatherApi.getWeather()
        return@withContext request.body()!!
    }
}