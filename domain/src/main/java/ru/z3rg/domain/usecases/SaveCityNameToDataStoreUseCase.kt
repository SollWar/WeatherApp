package ru.z3rg.domain.usecases

import ru.z3rg.domain.repository.UserDataStoreRepository
import javax.inject.Inject

class SaveCityNameToDataStoreUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend fun invoke(cityName: String) {
        userDataStoreRepository.setCityName(cityName)
    }
}