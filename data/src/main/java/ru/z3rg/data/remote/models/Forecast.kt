package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Forecast(

    @SerializedName("forecastday") var forecastday: ArrayList<Forecastday> = arrayListOf()

)