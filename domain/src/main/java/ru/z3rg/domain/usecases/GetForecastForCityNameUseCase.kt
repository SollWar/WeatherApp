package ru.z3rg.domain.usecases

import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastForCityNameUseCase @Inject constructor(private val weatherRepository: WeatherRepository) {

    suspend fun invoke(cityName: String): ForecastWeather {
        return weatherRepository.getForecast(cityName)
    }

}