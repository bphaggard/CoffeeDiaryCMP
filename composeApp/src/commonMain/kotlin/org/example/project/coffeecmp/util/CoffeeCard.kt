package org.example.project.coffeecmp.util

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import org.example.project.coffeecmp.ui.screens.SaveCoffeeScreen
import org.example.project.coffeecmp.ui.theme.CoffeeDarks
import org.example.project.coffeecmp.ui.theme.CoffeeLights
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.painterResource

@Composable
fun CoffeeCard(
    image : DrawableResource,
    title : String
){
    val colors = if (!isSystemInDarkTheme()) CoffeeLights else CoffeeDarks
    val navigator = LocalNavigator.currentOrThrow

    MaterialTheme(colorScheme = colors) {
        Card(
            modifier = Modifier
                .fillMaxWidth(0.85f)
                .wrapContentHeight(Alignment.CenterVertically),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
            elevation = CardDefaults.cardElevation(defaultElevation = 6.dp)
        ) {
            //val fontSize = with(LocalDensity.current) { 8.sp.toPx() }
            Row(
                modifier = Modifier
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Image(
                    painter = painterResource(image),
                    modifier = Modifier
                        .size(100.dp),
                    contentDescription = null
                )
                //Spacer(modifier = Modifier.padding(start = 20.dp))
                Text(
                    modifier = Modifier
                        .padding(bottom = 47.dp)
                        .width(95.dp),
                    text = title,
                    fontSize = 22.sp,
                    fontFamily = GetBebasFontFamily()
                )
                IconButton(
                    colors = IconButtonDefaults.iconButtonColors(MaterialTheme.colorScheme.scrim),
                    onClick = { navigator.push(SaveCoffeeScreen(title)) }
                ) {
                    Icon(
                        imageVector = Icons.Rounded.Add,
                        contentDescription = "add button",
                        tint = Color.White
                    )
                }
            }
        }
    }
}