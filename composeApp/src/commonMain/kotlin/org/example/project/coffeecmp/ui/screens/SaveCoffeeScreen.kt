package org.example.project.coffeecmp.ui.screens

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
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.coffeecmp.util.GetBebasFontFamily
import org.example.project.coffeecmp.util.bounceClick

class SaveCoffeeScreen(
    private val title: String
): Screen {

    @Composable
    override fun Content() {
        val colors = MaterialTheme.colorScheme

        SaveCoffeeScreenContent(title, colors)
    }
}

@Composable
fun SaveCoffeeScreenContent(
    title: String,
    colors: ColorScheme
) {
    MaterialTheme(colorScheme = colors) {
        Scaffold(
            topBar = {
                AppBar(title)
            }, content = { innerPadding ->
                Column(
                    modifier = Modifier
                        .padding(innerPadding)
                        .fillMaxSize()
                        .verticalScroll(rememberScrollState()),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Top
                ) {
                    //SaveCard()
                    Spacer(modifier = Modifier.padding(15.dp))
                    Button(
                        modifier = Modifier.bounceClick(),
                        onClick = {
                            //insertCoffee
                            //navigation + viewModel
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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar(title: String) {
    val navigator = LocalNavigator.currentOrThrow

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontFamily = GetBebasFontFamily(),
                fontSize = 30.sp)
        }, navigationIcon = {
            IconButton(onClick = {
                navigator.pop()
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = "Notes Button",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
    )
}