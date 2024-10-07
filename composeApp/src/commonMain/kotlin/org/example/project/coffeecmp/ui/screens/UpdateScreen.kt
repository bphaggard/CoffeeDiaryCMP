package org.example.project.coffeecmp.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import org.example.project.coffeecmp.CoffeeViewModel
import org.example.project.coffeecmp.navigation.Screen
import org.example.project.coffeecmp.ui.theme.CoffeeDarks
import org.example.project.coffeecmp.ui.theme.CoffeeLights
import org.example.project.coffeecmp.util.GetBebasFontFamily
import org.example.project.coffeecmp.util.RatingBar
import org.example.project.coffeecmp.util.UpdateCard
import org.example.project.coffeecmp.util.bounceClick
import org.koin.compose.KoinContext
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI

@OptIn(KoinExperimentalAPI::class)
@Composable
fun UpdateScreen(
    navController: NavController,
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
                topBar = { AppBar(navController) },
                content = { innerPadding ->
                    LazyColumn(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Top,
                        content = {
                            coffeeState?.let { coffee ->
                                item {
                                    val newLocation by viewModel.updateLocation.collectAsState()
                                    val newDescription by viewModel.updateDescription.collectAsState()
                                    val newRating by viewModel.updateRatingBar.collectAsState()
                                    val newDate by viewModel.updateDate.collectAsState()

                                    val focusManager = LocalFocusManager.current
                                    Card(
                                        modifier = Modifier
                                            .height(380.dp)
                                            .fillMaxWidth(0.85f),
                                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.surface)
                                    ) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxSize()
                                                .padding(15.dp),
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            Spacer(modifier = Modifier.padding(top = 15.dp))

                                            // Date (Display only)
                                            OutlinedCard(
                                                modifier = Modifier
                                                    .height(66.dp)
                                                    .fillMaxWidth(0.9f),
                                                shape = RoundedCornerShape(6.dp),
                                                colors = CardDefaults.cardColors(Color.Transparent),
                                                border = BorderStroke(1.dp, Color.Black)
                                            ) {
                                                Row(
                                                    modifier = Modifier
                                                        .fillMaxSize()
                                                        .padding(start = 14.dp),
                                                    verticalAlignment = Alignment.CenterVertically,
                                                    horizontalArrangement = Arrangement.SpaceBetween
                                                ) {
                                                    Text("Date: $newDate")
                                                }
                                            }

                                            // Location (Editable)
                                            OutlinedTextField(
                                                modifier = Modifier
                                                    .height(66.dp)
                                                    .fillMaxWidth(0.9f),
                                                maxLines = 1,
                                                value = newLocation,
                                                label = { Text(text = "Location") },
                                                keyboardOptions = KeyboardOptions(
                                                    imeAction = ImeAction.Next
                                                ),
                                                keyboardActions = KeyboardActions(
                                                    onNext = {
                                                        focusManager.moveFocus(FocusDirection.Down)
                                                    }
                                                ),
                                                placeholder = { Text(text = "Input location") },
                                                onValueChange = { newValue ->
                                                    viewModel.updateLocation(newValue)
                                                },
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedTextColor = Color.Black,
                                                    unfocusedTextColor = Color.Black,
                                                    focusedBorderColor = Color.Black,
                                                    unfocusedBorderColor = Color.Black,
                                                    focusedLabelColor = Color.Black,
                                                    unfocusedLabelColor = Color.Black,
                                                )
                                            )

                                            // Description (Editable)
                                            OutlinedTextField(
                                                modifier = Modifier
                                                    .height(150.dp)
                                                    .fillMaxWidth(0.9f),
                                                value = newDescription,
                                                label = { Text(text = "Description") },
                                                keyboardOptions = KeyboardOptions(
                                                    imeAction = ImeAction.Done
                                                ),
                                                keyboardActions = KeyboardActions(
                                                    onNext = {
                                                        focusManager.moveFocus(FocusDirection.Down)
                                                    }
                                                ),
                                                placeholder = { Text(text = "Input description") },
                                                onValueChange = { newValue ->
                                                    viewModel.updateDescription(newValue)
                                                },
                                                minLines = 2,
                                                maxLines = 6,
                                                colors = OutlinedTextFieldDefaults.colors(
                                                    focusedTextColor = Color.Black,
                                                    unfocusedTextColor = Color.Black,
                                                    focusedBorderColor = Color.Black,
                                                    unfocusedBorderColor = Color.Black,
                                                    focusedLabelColor = Color.Black,
                                                    unfocusedLabelColor = Color.Black,
                                                )
                                            )

                                            Spacer(modifier = Modifier.padding(10.dp))

                                            // Rating
                                            Row {
                                                Text(
                                                    text = "Rating:",
                                                    fontFamily = GetBebasFontFamily(),
                                                    fontSize = 24.sp
                                                )
                                                Spacer(modifier = Modifier.padding(5.dp))
                                                RatingBar(
                                                    currentRating = newRating,
                                                    onRatingChanged = { newValue ->
                                                        viewModel.updateRatingBar(newValue)
                                                    }
                                                )
                                            }
                                        }
                                    }
                                    Spacer(modifier = Modifier.padding(15.dp))
                                    Button(
                                        modifier = Modifier.bounceClick(),
                                        onClick = {
                                            coffee.id?.let {
                                                viewModel.updateCoffee(it, newDate, newLocation, newDescription, newRating.toLong())
                                            }
                                            navController.navigate(Screen.Note.route)
                                        },
                                        shape = RoundedCornerShape(20.dp),
                                        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                                    ) {
                                        Text(
                                            text = "Update Coffee",
                                            fontFamily = GetBebasFontFamily(),
                                            fontSize = 26.sp
                                        )
                                    }
                                }
                            }
                        }
                    )
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
                text = "Update Note",
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