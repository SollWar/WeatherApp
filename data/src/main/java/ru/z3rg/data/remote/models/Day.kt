package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Day(

    @SerializedName("mintemp_c") val mintempC: Double? = null,
    @SerializedName("maxtemp_c") val maxtempC: Double? = null,
    @SerializedName("avgtemp_c") val avgtempC: Double? = null,
    @SerializedName("maxwind_kph") val maxwindKph: Double? = null,
    @SerializedName("avghumidity") val avghumidity: Double? = null,
    @SerializedName("daily_chance_of_rain") val dailyChanceOfRain: Double? = null,
    @SerializedName("condition") val condition: Condition? = Condition(),

    )