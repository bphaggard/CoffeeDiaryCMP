package org.example.project.coffeecmp.util

import coffeecmp.composeapp.generated.resources.Res
import coffeecmp.composeapp.generated.resources.americano
import coffeecmp.composeapp.generated.resources.cappuccino
import coffeecmp.composeapp.generated.resources.chocolate
import coffeecmp.composeapp.generated.resources.espresso
import coffeecmp.composeapp.generated.resources.flatwhite
import coffeecmp.composeapp.generated.resources.latte
import org.jetbrains.compose.resources.DrawableResource

data class CoffeeTypes(
    val id: Int,
    val imageId: DrawableResource,
    val title: String)

val coffeeTypes = arrayListOf(
    CoffeeTypes(0, Res.drawable.espresso, "Espresso"),
    CoffeeTypes(1, Res.drawable.americano, "Americano"),
    CoffeeTypes(2, Res.drawable.cappuccino, "Cappuccino"),
    CoffeeTypes(3, Res.drawable.latte, "Latte"),
    CoffeeTypes(4, Res.drawable.flatwhite, "Flat White"),
    CoffeeTypes(5, Res.drawable.chocolate, "Chocolate")
)