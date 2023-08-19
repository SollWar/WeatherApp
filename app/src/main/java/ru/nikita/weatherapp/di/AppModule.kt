package ru.nikita.weatherapp.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.nikita.weatherapp.R
import ru.z3rg.data.local.datastore.userDataStore
import ru.z3rg.data.local.repository.UserDataStoreRepositoryImpl
import ru.z3rg.data.remote.repository.WeatherRepositoryImpl
import ru.z3rg.data.remote.retrofit.WeatherApi
import ru.z3rg.domain.repository.UserDataStoreRepository
import ru.z3rg.domain.repository.WeatherRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    private fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://api.weatherapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {
        return provideRetrofit()
            .create(WeatherApi::class.java)
    }

    @Provides
    fun provideCurrentLocale(@ApplicationContext applicationContext: Context): String {
        return applicationContext.resources.getString(R.string.current_locale)
    }

    @Provides
    @Singleton
    fun provideWeatherRepository(weatherApi: WeatherApi): WeatherRepository {
        return WeatherRepositoryImpl(weatherApi)
    }

    @Provides
    @Singleton
    fun provideUserDataStore(@ApplicationContext applicationContext: Context): DataStore<Preferences> {
        return applicationContext.userDataStore
    }

    @Provides
    @Singleton
    fun provideUserDataStoreRepository(userDataStorePreferences: DataStore<Preferences>): UserDataStoreRepository {
        return UserDataStoreRepositoryImpl(userDataStorePreferences)
    }
}