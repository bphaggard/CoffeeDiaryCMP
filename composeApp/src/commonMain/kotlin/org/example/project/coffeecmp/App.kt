package org.example.project.coffeecmp

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import org.example.project.coffeecmp.navigation.SetupNavGraph
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@Composable
@Preview
fun App() {
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        KoinContext {
            val navController = rememberNavController()
            SetupNavGraph(navController = navController)
        }
    }
}