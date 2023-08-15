package ru.z3rg.domain.models

data class CityEntity(
    val name: String,
    val region: String,
    val country: String,
    val lat: Double,
    val lon: Double
)
