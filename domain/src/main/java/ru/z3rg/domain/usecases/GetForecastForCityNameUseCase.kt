package ru.z3rg.domain.usecases

import ru.z3rg.domain.models.ForecastWeather
import ru.z3rg.domain.models.Result
import ru.z3rg.domain.repository.WeatherRepository
import javax.inject.Inject

class GetForecastForCityNameUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository,
    private val currentLocale: String
) {

    suspend fun invoke(cityName: String): Result<ForecastWeather> {
        return weatherRepository.getForecast(cityName, currentLocale)
    }

}