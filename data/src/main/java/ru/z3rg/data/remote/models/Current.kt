package ru.z3rg.data.remote.models

data class Current(
    val condition: Condition,
    val humidity: Double,
    val temp_c: Double,
    val wind_kph: Double,
)