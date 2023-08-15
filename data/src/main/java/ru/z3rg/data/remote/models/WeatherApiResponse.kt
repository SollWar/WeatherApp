package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName

data class WeatherApiResponse(
    @SerializedName("location" ) val location : Location,
    @SerializedName("forecast" ) val forecast : Forecast
)