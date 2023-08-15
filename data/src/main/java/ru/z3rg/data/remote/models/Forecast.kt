package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Forecast(
    @SerializedName("forecastday") val forecastday: ArrayList<Forecastday> = arrayListOf()
)