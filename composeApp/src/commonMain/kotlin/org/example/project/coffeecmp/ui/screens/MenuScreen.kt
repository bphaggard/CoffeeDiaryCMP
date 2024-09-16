package org.example.project.coffeecmp.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import org.example.project.coffeecmp.util.GetBebasFontFamily

class MenuScreen: Screen {

    @Composable
    override fun Content() {
        MenuScreenContent()
    }
}

@Composable
fun MenuScreenContent() {
    Column {
        AppBar()
        Text("Menu Screen")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun AppBar() {
    TopAppBar(
        title = {
            Text(
                text = "choose your coffee:",
                fontFamily = GetBebasFontFamily(),
                fontSize = 30.sp)
        },
        actions = {
            IconButton(onClick = {
//                navigator.push(SourcesScreen())
            }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.List,
                    contentDescription = "Notes Button",
                )
            }
        }
    )
}