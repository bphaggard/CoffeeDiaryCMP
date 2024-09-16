package org.example.project.coffeecmp.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Notes
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.coffeecmp.ui.theme.CoffeeDarks
import org.example.project.coffeecmp.ui.theme.CoffeeLights
import org.example.project.coffeecmp.util.CoffeeCard
import org.example.project.coffeecmp.util.GetBebasFontFamily
import org.example.project.coffeecmp.util.coffeeTypes

class MenuScreen: Screen {

    @Composable
    override fun Content() {
        MenuScreenContent()
    }
}

@Composable
fun MenuScreenContent() {
    val colors = if (!isSystemInDarkTheme()) CoffeeLights else CoffeeDarks

    MaterialTheme(colorScheme = colors) {
        Column(
            modifier = Modifier.background(MaterialTheme.colorScheme.background)
        ) {
            AppBar()
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(coffeeTypes) { coffeeType ->
                    CoffeeCard(
                        image = coffeeType.imageId,
                        title = coffeeType.title
                    )
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "choose your coffee:",
                fontFamily = GetBebasFontFamily(),
                fontSize = 30.sp)
        }, navigationIcon = {
            IconButton(onClick = {
//                navigator.push(SourcesScreen())
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.Notes,
                    contentDescription = "Notes Button",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
    )
}