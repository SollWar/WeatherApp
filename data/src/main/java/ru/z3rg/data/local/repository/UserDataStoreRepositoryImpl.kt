package ru.z3rg.data.local.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import ru.z3rg.domain.repository.UserDataStoreRepository
import javax.inject.Inject

class UserDataStoreRepositoryImpl @Inject constructor(
    private val userDataStorePreferences: DataStore<Preferences>
) : UserDataStoreRepository {

    override suspend fun getCityName(): String {
        val flow = userDataStorePreferences.data
            .map { preferences ->
                preferences[stringPreferencesKey("cityName")]
            }
        return flow.first() ?: "Москва"
    }

    override suspend fun getCityCoordLat(): Double {
        val flow = userDataStorePreferences.data
            .map { preferences ->
                preferences[doublePreferencesKey("lat")]
            }
        return flow.first() ?: 55.75
    }

    override suspend fun getCityCoordLon(): Double {
        val flow = userDataStorePreferences.data
            .map { preferences ->
                preferences[doublePreferencesKey("lon")]
            }
        return flow.first() ?: 37.62
    }

    override suspend fun setCityName(cityName: String) {
        userDataStorePreferences.edit { preferences ->
            preferences[stringPreferencesKey("cityName")] = cityName
        }
    }

    override suspend fun setCityCoord(lat: Double, lon: Double) {
        userDataStorePreferences.edit { preferences ->
            preferences[doublePreferencesKey("lat")] = lat
            preferences[doublePreferencesKey("lon")] = lon
        }
    }


}