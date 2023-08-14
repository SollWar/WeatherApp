package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Forecastday(

    @SerializedName("date") var date: String? = null,
    @SerializedName("day") var day: Day? = Day()

)