package org.example.project.coffeecmp.domain

import app.cash.sqldelight.Query

interface CoffeeDataSource {
    suspend fun insertCoffee(coffee: Coffee)
    suspend fun getCoffeeById(id: Long): Coffee?
    suspend fun getAllCoffees(): List<Coffee>
    suspend fun deleteCoffeeById(id: Long)
    suspend fun deleteAllCoffees()
    suspend fun updateCoffee(coffee: Coffee)
    suspend fun getCoffeeByTitle(): Query<List<Coffee>>
    suspend fun getCoffeeByDate(): Query<List<Coffee>>
    suspend fun getCoffeeByLocation(): Query<List<Coffee>>
}