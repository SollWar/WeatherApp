package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Day(

    @SerializedName("avgtemp_c") var avgtempC: Double? = null,
    @SerializedName("maxwind_kph") var maxwindKph: Int? = null,
    @SerializedName("avghumidity") var avghumidity: Int? = null,
    @SerializedName("daily_chance_of_rain") var dailyChanceOfRain: Int? = null,
    @SerializedName("condition") var condition: Condition? = Condition(),

    )