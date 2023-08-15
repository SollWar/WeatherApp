package ru.z3rg.data.remote.retrofit

import dagger.Provides
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import ru.z3rg.data.remote.Constants
import ru.z3rg.data.remote.models.CitySearch
import ru.z3rg.data.remote.models.WeatherApiResponse


interface WeatherApi {

    @Provides
    @GET("/v1/forecast.json")
    suspend fun getWeather(
        @Query("key") key: String = Constants().API_KEY,
        @Query("q") city: String = "Москва",
        @Query("days") days: Int = 5,
        @Query("aqi") aqi: String = "no",
        @Query("alerts") alerts: String = "no",
        @Query("lang") lang: String = "ru"
    ) : Response<WeatherApiResponse>

    @Provides
    @GET("/v1/search.json")
    suspend fun searchCity(
        @Query("key") key: String = Constants().API_KEY,
        @Query("q") cityName: String = "Москва"
    ) : Response<CitySearch>
}