package org.example.project.coffeecmp.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coffeecmp.composeapp.generated.resources.Res
import coffeecmp.composeapp.generated.resources.espresso
import org.example.project.coffeecmp.CoffeeViewModel
import org.example.project.coffeecmp.navigation.Screen
import org.example.project.coffeecmp.ui.theme.CoffeeDarks
import org.example.project.coffeecmp.ui.theme.CoffeeLights
import org.example.project.coffeecmp.util.GetBebasFontFamily
import org.example.project.coffeecmp.util.GetOswaldFontLightFamily
import org.example.project.coffeecmp.util.GetOswaldFontRegularFamily
import org.example.project.coffeecmp.util.RatingBar
import org.example.project.coffeecmp.util.titleToImageMap
import org.jetbrains.compose.resources.painterResource
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun DetailScreen(
    navController: NavController,
    title: String,
    itemId: Long
) {
    val viewModel = koinViewModel<CoffeeViewModel>()
    val coffeeState by viewModel.coffee.collectAsState(initial = null)
    val colors = if (!isSystemInDarkTheme()) CoffeeLights else CoffeeDarks

    LaunchedEffect(itemId) {
        viewModel.getCoffeeById(itemId)
    }

    MaterialTheme(colorScheme = colors) {
        KoinContext {
            Scaffold(
                topBar = { AppBar(navController, title) },
                content = { innerPadding ->
                    Column(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top
                    ) {
                        Card(
                            modifier = Modifier
                                .fillMaxWidth(0.85f)
                                .height(200.dp),
                            shape = RoundedCornerShape(22.dp),
                            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp),
                        ) {
                            val coffeeImage = titleToImageMap[title]?: Res.drawable.espresso

                            Column(
                                modifier = Modifier.fillMaxSize(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    painter = painterResource(coffeeImage),
                                    modifier = Modifier
                                        .fillMaxSize(0.85f),
                                    contentDescription = null
                                )
                            }
                        }
                        Spacer(modifier = Modifier.padding(15.dp))
                        LazyColumn(
                            modifier = Modifier
                                .fillMaxHeight()
                                .fillMaxWidth(0.85f),
                            horizontalAlignment = Alignment.Start,
                            verticalArrangement = Arrangement.Top
                        ) {
                            coffeeState?.let { coffee ->
                                item {
                                    Column(
                                        modifier = Modifier
                                            .padding(16.dp)
                                    ) {
                                        Row(
                                            modifier = Modifier.fillMaxSize(),
                                            verticalAlignment = Alignment.CenterVertically,
                                            horizontalArrangement = Arrangement.SpaceBetween
                                        ) {
                                            Column(
                                                modifier = Modifier.fillMaxWidth(0.7f)
                                            ) {
                                                LocationExpandedText(
                                                    text = coffee.location
                                                )
                                            }
                                            Spacer(modifier = Modifier.width(5.dp))
                                            Column(
                                                modifier = Modifier.fillMaxWidth()
                                            ) {
                                                Text(
                                                    text = coffee.date,
                                                    fontFamily = GetOswaldFontLightFamily()
                                                )
                                            }
                                        }
                                        Spacer(modifier = Modifier.height(10.dp))
                                        Row {
                                            Text(
                                                text = "rating:",
                                                fontFamily = GetOswaldFontLightFamily(),
                                                fontSize = 16.sp
                                            )
                                            Spacer(modifier = Modifier.padding(5.dp))
                                            RatingBar(
                                                currentRating = coffee.ratingBar,
                                                onRatingChanged = { }
                                            )
                                        }
                                        Spacer(modifier = Modifier.padding(5.dp))
                                        Text(
                                            text = coffee.description,
                                            fontFamily = GetOswaldFontRegularFamily(),
                                            fontSize = 20.sp
                                        )
                                    }
                                }
                            }
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
    navController: NavController,
    title: String
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                fontFamily = GetBebasFontFamily(),
                fontSize = 30.sp)
        }, navigationIcon = {
            IconButton(onClick = {
                navController.popBackStack(Screen.Note.route, inclusive = false)
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

@Composable
fun LocationExpandedText(text: String) {
    var isExpanded by remember { mutableStateOf(false) }

    Text(
        text = text,
        fontFamily = GetOswaldFontLightFamily(),
        fontWeight = FontWeight.Light,
        maxLines = if (isExpanded) Int.MAX_VALUE else 1,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier.clickable { isExpanded = !isExpanded }
    )
}