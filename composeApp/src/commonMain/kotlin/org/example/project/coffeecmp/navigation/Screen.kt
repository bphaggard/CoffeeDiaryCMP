package org.example.project.coffeecmp.navigation

sealed class Screen(val route: String) {
    data object Main: Screen(route = "main")
    data object Menu: Screen(route = "menu")
    data object Save: Screen(route = "save")
    data object Note: Screen(route = "note")
    data object Detail: Screen(route = "detail")
    data object Update: Screen(route = "update")
}