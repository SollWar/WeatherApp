package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName

data class Hour(
    val time: String,
    @SerializedName("temp_c") val tempC: Double,
    val condition: Condition,
    @SerializedName("wind_kph") val windKhp: Double,
    val humidity: Double,
    @SerializedName("chance_of_rain") val chanceOfRain: Double
)