package ru.z3rg.domain.usecases

import ru.z3rg.domain.models.CityList
import ru.z3rg.domain.repository.WeatherRepository
import javax.inject.Inject

class FindCityByCityNameUseCase @Inject constructor(
    private val weatherRepository: WeatherRepository
) {

    suspend fun invoke(cityName: String): CityList {
        return weatherRepository.searchCity(cityName)
    }

}