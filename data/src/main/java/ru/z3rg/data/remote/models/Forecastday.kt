package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Forecastday(

    @SerializedName("date") val date: String? = null,
    @SerializedName("day") val day: Day? = Day()

)