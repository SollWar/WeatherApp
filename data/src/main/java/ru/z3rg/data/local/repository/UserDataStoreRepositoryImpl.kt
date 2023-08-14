package ru.z3rg.data.local.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
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

    override suspend fun setCityName(cityName: String) {
        userDataStorePreferences.edit { preferences ->
            preferences[stringPreferencesKey("cityName")] = cityName
        }
    }


}