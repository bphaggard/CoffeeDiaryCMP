package org.example.project.coffeecmp.ui.screens

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.coffeecmp.CoffeeViewModel
import org.example.project.coffeecmp.navigation.Screen
import org.example.project.coffeecmp.ui.theme.CoffeeDarks
import org.example.project.coffeecmp.ui.theme.CoffeeLights
import org.example.project.coffeecmp.util.GetBebasFontFamily
import org.example.project.coffeecmp.util.RatingBar
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun NoteScreenContent(
    navController : NavController
) {
    val colors = if (!isSystemInDarkTheme()) CoffeeLights else CoffeeDarks
    val viewModel = koinViewModel<CoffeeViewModel>()
    val coffeeList = viewModel.coffees.collectAsState()

    MaterialTheme(colorScheme = colors) {
        KoinContext {
            Scaffold(
                topBar = { AppBar(navController) },
                content = { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(14.dp)
                    ) {
                        items(coffeeList.value.size) { index ->
                            val coffee = coffeeList.value[index]
                            Card(
                                modifier = Modifier
                                    .fillMaxWidth(0.85f)
                                    .height(120.dp)
                                    .clickable {  },
                                shape = RoundedCornerShape(22.dp),
                                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                                elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(horizontal = 14.dp),
                                    verticalAlignment = Alignment.CenterVertically,
                                    horizontalArrangement = Arrangement.SpaceBetween
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(0.85f)
                                            .padding(top = 10.dp, end = 10.dp),
                                        verticalArrangement = Arrangement.Center
                                    ) {
                                        Text(
                                            text = coffee.title,
                                            fontFamily = GetBebasFontFamily(),
                                            fontSize = 24.sp
                                        )
                                        Text(
                                            text = coffee.location,
                                            fontFamily = GetBebasFontFamily(),
                                            maxLines = 1,
                                            overflow = TextOverflow.Ellipsis,
                                            fontSize = 14.sp
                                        )
                                        Text(
                                            text = coffee.date,
                                            fontFamily = GetBebasFontFamily(),
                                            fontSize = 10.sp
                                        )
                                        RatingBar(
                                            currentRating = coffee.ratingBar,
                                            onRatingChanged = {})
                                    }
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Column(
                                        modifier = Modifier
                                            .fillMaxHeight()
                                            .fillMaxWidth(0.85f)
                                            .padding(vertical = 15.dp),
                                        verticalArrangement = Arrangement.SpaceBetween
                                    ) {
                                        Icon(
                                            imageVector = Icons.Filled.Edit,
                                            contentDescription = "edit",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .clickable(onClick = {  })
                                        )
                                        Icon(
                                            imageVector = Icons.Filled.Delete,
                                            contentDescription = "delete",
                                            tint = Color.Black,
                                            modifier = Modifier
                                                .clickable(onClick = {
                                                    coffee.id?.let { viewModel.deleteCoffeeById(it) }
                                                }
                                            )
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
    navController: NavController
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = "your diaries:",
                fontFamily = GetBebasFontFamily(),
                fontSize = 30.sp)
        }, navigationIcon = {
            IconButton(onClick = {
                navController.navigate(Screen.Menu.route)
            }) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home Button",
                )
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color.Transparent)
    )
}