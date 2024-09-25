package org.example.project.coffeecmp.domain

data class Coffee(
    val id: Long? = null,
    val title: String,
    val location: String,
    val description: String,
    val date: String,
    val ratingBar: Int
)