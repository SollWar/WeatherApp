package ru.z3rg.domain.repository

interface UserDataStoreRepository {

    suspend fun getCityName(): String
    suspend fun getCityCoordLat(): Double
    suspend fun getCityCoordLon(): Double
    suspend fun setCityName(cityName: String)
    suspend fun setCityCoord(lat: Double, lon: Double)

}