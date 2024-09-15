package org.example.project.coffeecmp

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import org.example.project.coffeecmp.ui.theme.CoffeeDarks
import org.example.project.coffeecmp.ui.theme.CoffeeLights
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    val colors = if (!isSystemInDarkTheme()) CoffeeLights else CoffeeDarks

    MaterialTheme(colorScheme = colors) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.primary),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "coffee diary",
                fontSize = 40.sp,
                color = MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}