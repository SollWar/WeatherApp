package ru.z3rg.domain.models

data class Result<T>(
    val body: T?,
    val errorMessage: String?
)