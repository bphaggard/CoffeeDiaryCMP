package org.example.project.coffeecmp.domain

fun database.Coffee.toCoffee(): Coffee {
    return Coffee(
        id = id,
        title = title,
        location = location,
        description = description,
        date = date,
        ratingBar = ratingBar.toInt()
    )
}