package ru.z3rg.domain.usecases

import ru.z3rg.domain.repository.UserDataStoreRepository
import javax.inject.Inject

// Неверно назвал класс, а после инъекции его через Hilt при рефактроинге названия он его не находил
class GetForecastForCityNameFromDataStoreUseCase @Inject constructor(
    private val userDataStoreRepository: UserDataStoreRepository
)
{
    suspend fun invoke(): String {
        return userDataStoreRepository.getCityName()
    }
}