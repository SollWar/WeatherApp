package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Location(

    @SerializedName("name") val name: String? = null,
    @SerializedName("region") val region: String? = null,
    @SerializedName("country") val country: String? = null

)