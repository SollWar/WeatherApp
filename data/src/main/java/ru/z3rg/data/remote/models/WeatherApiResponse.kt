package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName

data class WeatherApiResponse(
    @SerializedName("location" ) var location : Location? = Location(),
    @SerializedName("forecast" ) var forecast : Forecast? = Forecast()
)