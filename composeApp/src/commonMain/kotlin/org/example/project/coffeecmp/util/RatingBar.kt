package org.example.project.coffeecmp.util

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp

@Composable
fun RatingBar(
    maxRating: Int = 5,
    currentRating: Int = 0,
    onRatingChanged: (Int) -> Unit
) {
    Row {
        for (i in 1..maxRating) {
            Icon(
                imageVector = if (i <= currentRating) Icons.Filled.Star else Icons.Filled.Star,
                contentDescription = "Rating Star",
                tint = if (i <= currentRating) androidx.compose.ui.graphics.Color.Yellow else androidx.compose.ui.graphics.Color.Gray,
                modifier = androidx.compose.ui.Modifier
                    .padding(4.dp)
                    .height(20.dp)
                    .clickable { onRatingChanged(i) }
            )
        }
    }
}