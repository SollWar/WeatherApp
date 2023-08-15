package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Day(
    @SerializedName("mintemp_c") val mintempC: Double,
    @SerializedName("maxtemp_c") val maxtempC: Double,
    @SerializedName("avgtemp_c") val avgtempC: Double,
    @SerializedName("maxwind_kph") val maxwindKph: Double,
    @SerializedName("avghumidity") val avghumidity: Double,
    @SerializedName("daily_chance_of_rain") val dailyChanceOfRain: Double,
    @SerializedName("condition") val condition: Condition
)