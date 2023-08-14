package ru.z3rg.domain.repository

interface UserDataStoreRepository {

    suspend fun getCityName(): String
    suspend fun setCityName(cityName: String)

}