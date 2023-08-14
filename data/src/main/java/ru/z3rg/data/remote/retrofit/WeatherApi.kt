package ru.z3rg.data.remote.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.z3rg.data.remote.models.WeatherApiResponse

interface WeatherApi {
    @GET("/v1/forecast.json")
    suspend fun getWeather(
        @Query("key") key: String = "92b3af19392d4368b4394116231308",
        @Query("q") city: String = "Moscow",
        @Query("days") days: Int = 5,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
        @Query("lang") lang: String = "ru"
    ) : Response<WeatherApiResponse>
}