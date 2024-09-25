package org.example.project.coffeecmp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coffeecmp.composeapp.generated.resources.Res
import coffeecmp.composeapp.generated.resources.cap2
import org.example.project.coffeecmp.navigation.Screen
import org.example.project.coffeecmp.ui.theme.CoffeeDarks
import org.example.project.coffeecmp.ui.theme.CoffeeLights
import org.example.project.coffeecmp.util.GetBebasFontFamily
import org.example.project.coffeecmp.util.GetDjbCoffeeFontFamily
import org.example.project.coffeecmp.util.bounceClick
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinContext

@Composable
fun MainScreenContent(
    navController : NavController
) {
    val colors = if (!isSystemInDarkTheme()) CoffeeLights else CoffeeDarks

    MaterialTheme(colorScheme = colors) {
        KoinContext {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MaterialTheme.colorScheme.primary)
                    .verticalScroll(rememberScrollState()),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Spacer(modifier = Modifier.fillMaxHeight(0.3f))
                Text(
                    text = "coffee diary",
                    fontSize = 40.sp,
                    fontFamily = GetDjbCoffeeFontFamily(),
                    color = MaterialTheme.colorScheme.onPrimary
                )
                Image(
                    modifier = Modifier.fillMaxWidth(0.8f),
                    painter = painterResource(Res.drawable.cap2),
                    contentDescription = "Main Image"
                )
                Spacer(modifier = Modifier.fillMaxHeight(0.2f))
                Button(
                    modifier = Modifier
                        .width(200.dp)
                        .bounceClick(),
                    onClick = { navController.navigate(Screen.Menu.route) },
                    shape = RoundedCornerShape(15.dp),
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                )
                {
                    Text(
                        text = "Get Started",
                        fontFamily = GetBebasFontFamily(),
                        fontSize = 26.sp
                    )
                }
                Spacer(modifier = Modifier.fillMaxHeight(0.3f))
            }
        }
    }
}