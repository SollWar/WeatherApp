package ru.nikita.weatherapp.ui.base

interface EventHandler<T> {
    fun obtainEvent(event: T)
}