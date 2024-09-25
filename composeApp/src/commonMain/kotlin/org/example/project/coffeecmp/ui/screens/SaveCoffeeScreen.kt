package org.example.project.coffeecmp.ui.screens

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.coffeecmp.CoffeeViewModel
import org.example.project.coffeecmp.navigation.Screen
import org.example.project.coffeecmp.ui.theme.CoffeeDarks
import org.example.project.coffeecmp.ui.theme.CoffeeLights
import org.example.project.coffeecmp.util.GetBebasFontFamily
import org.example.project.coffeecmp.util.SaveCard
import org.example.project.coffeecmp.util.bounceClick
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.scope.ScopeID

@OptIn(KoinExperimentalAPI::class)
@Composable
fun SaveCoffeeScreenContent(
    title: String,
    navController : NavController
) {
    val colors = if (!isSystemInDarkTheme()) CoffeeLights else CoffeeDarks
    val viewModel = koinViewModel<CoffeeViewModel>()

    MaterialTheme(colorScheme = colors) {
        KoinContext {
            Scaffold(
                topBar = {
                    AppBar(title, navController)
                }, content = { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .verticalScroll(rememberScrollState()),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        SaveCard(viewModel)
                        Spacer(modifier = Modifier.padding(15.dp))
                        Button(
                            modifier = Modifier.bounceClick(),
                            onClick = {
                                viewModel.saveCoffee(title)
                                navController.navigate(Screen.Note.route)
                            },
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                        ) {
                            Text(
                                text = "save note",
                                fontFamily = GetBebasFontFamily(),
                                fontSize = 26.sp
                            )
                        }
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(
    title: String,
    navController: NavController
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontFamily = GetBebasFontFamily(),
                fontSize = 30.sp)
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack("menu", inclusive = false)
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Back Button",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
    )
}