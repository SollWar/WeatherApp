package ru.z3rg.data.remote.models

import com.google.gson.annotations.SerializedName


data class Condition(

    @SerializedName("text") val text: String? = null,
    @SerializedName("icon") val icon: String? = null

)