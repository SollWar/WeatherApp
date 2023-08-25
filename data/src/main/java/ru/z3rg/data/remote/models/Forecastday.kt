package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Forecastday(
    @SerializedName("date") val date: String,
    @SerializedName("day") val day: Day,
    val hour: ArrayList<Hour> = arrayListOf()
)