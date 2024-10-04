package org.example.project.coffeecmp.util

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.example.project.coffeecmp.CoffeeViewModel

@Composable
fun SaveCard(viewModel: CoffeeViewModel) {
    // Observing values from the ViewModel
    val location by viewModel.newLocation.collectAsState()
    val description by viewModel.newDescription.collectAsState()
    val rating by viewModel.newRatingBar.collectAsState()
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
                    Text("Date: ${viewModel.formattedDate}")
                }
            }
            OutlinedTextField(
                modifier = Modifier
                    .height(66.dp)
                    .fillMaxWidth(0.9f),
                maxLines = 1,
                value = location,
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
                onValueChange = { viewModel.setLocation(it) },
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedBorderColor = Color.Black,
                    unfocusedBorderColor = Color.Black,
                    focusedLabelColor = Color.Black,
                    unfocusedLabelColor = Color.Black,
                )
            )
            OutlinedTextField(
                modifier = Modifier
                    .height(150.dp)
                    .fillMaxWidth(0.9f),
                value = description,
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
                onValueChange = { viewModel.setDescription(it) },
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
            Row {
                Text(
                    text = "rating:",
                    fontFamily = GetBebasFontFamily(),
                    fontSize = 24.sp
                )
                Spacer(modifier = Modifier.padding(5.dp))
                RatingBar(
                    currentRating = rating,
                    onRatingChanged = { viewModel.setRating(it) }
                )
            }
        }
    }
}