package ru.nikita.weatherapp

sealed class Screen(val route: String) {
    data object MainScreen: Screen("main_screen")
    data object SearchScreen: Screen("search_screen")
}