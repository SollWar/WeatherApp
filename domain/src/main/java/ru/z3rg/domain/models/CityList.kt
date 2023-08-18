package ru.z3rg.domain.models

data class CityList(
    val cityList: ArrayList<CityEntity> = arrayListOf(),
    val error: String? = null
) {
    fun addCityEntity(cityEntity: CityEntity) {
        cityList.add(cityEntity)
    }
}