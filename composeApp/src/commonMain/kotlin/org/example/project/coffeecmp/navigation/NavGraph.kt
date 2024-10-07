package org.example.project.coffeecmp.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import org.example.project.coffeecmp.ui.screens.DetailScreen
import org.example.project.coffeecmp.ui.screens.MainScreenContent
import org.example.project.coffeecmp.ui.screens.MenuScreenContent
import org.example.project.coffeecmp.ui.screens.NoteScreenContent
import org.example.project.coffeecmp.ui.screens.SaveCoffeeScreenContent
import org.example.project.coffeecmp.ui.screens.UpdateScreen

@Composable
fun SetupNavGraph(
    navController: NavHostController,
    startDestination: String = Screen.Main.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(route = Screen.Main.route) {
            MainScreenContent(navController)
        }
        composable(route = Screen.Menu.route) {
            MenuScreenContent(navController)
        }
        composable(route = "${Screen.Save.route}/{title}") {
            navBackStackEntry ->
            val title = navBackStackEntry.arguments?.getString("title")
            SaveCoffeeScreenContent(title ?: "", navController)
        }
        composable(route = Screen.Note.route) {
            NoteScreenContent(navController)
        }
        composable(route = "${Screen.Detail.route}/{title}/{id}") {
            navBackStackEntry ->
            val title = navBackStackEntry.arguments?.getString("title")
            val idString = navBackStackEntry.arguments?.getString("id")
            val id = idString?.toLongOrNull()
            if (id != null) {
                DetailScreen(navController, title ?: "", id)
            }
        }
        composable(route = "${Screen.Update.route}/{id}") {
            navBackStackEntry ->
            val idString = navBackStackEntry.arguments?.getString("id")
            val id = idString?.toLongOrNull()
            if (id != null) {
                UpdateScreen(navController, id)
            }
        }
    }
}