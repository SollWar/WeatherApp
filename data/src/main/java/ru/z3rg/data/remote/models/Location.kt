package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Location(

    @SerializedName("name") var name: String? = null,
    @SerializedName("region") var region: String? = null,
    @SerializedName("country") var country: String? = null

)