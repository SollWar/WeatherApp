package ru.z3rg.domain.usecases

import ru.z3rg.domain.repository.UserDataStoreRepository
import javax.inject.Inject

class SaveCordToDataStoreUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
) {
    suspend fun invoke(lat: Double, lon: Double) {
        userDataStoreRepository.setCityCoord(lat, lon)
    }
}