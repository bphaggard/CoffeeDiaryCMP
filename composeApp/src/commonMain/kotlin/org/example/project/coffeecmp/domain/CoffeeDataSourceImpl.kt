package org.example.project.coffeecmp.domain

import app.cash.sqldelight.Query
import org.example.project.coffeecmp.db.CoffeeDatabase

class CoffeeDataSourceImpl (db: CoffeeDatabase): CoffeeDataSource {
    private val coffeeQueries = db.appDatabaseQueries

    override suspend fun insertCoffee(coffee: Coffee) {
        coffeeQueries.insertCoffee(coffee.id, coffee.title, coffee.location, coffee.description, coffee.date, coffee.ratingBar.toLong())
    }

    override suspend fun getCoffeeById(id: Long): Coffee? {
        return coffeeQueries
            .getCoffeeById(id)
            .executeAsOneOrNull()?.toCoffee()
    }

    override suspend fun getAllCoffees(): List<Coffee> {
        return coffeeQueries
            .getAllCoffees()
            .executeAsList()
            .map { it.toCoffee() }
    }

    override suspend fun deleteCoffeeById(id: Long) {
        coffeeQueries.deleteCoffeeById(id)
    }

    override suspend fun deleteAllCoffees() {
        coffeeQueries.deleteAllCoffees()
    }

    override suspend fun updateCoffee(coffee: Coffee) {
        coffee.id?.let {
            coffeeQueries.updateCoffee(coffee.title, coffee.location, coffee.description, coffee.date, coffee.ratingBar.toLong(),
                it
            )
        }
    }

    override suspend fun getCoffeeByTitle(): Query<List<Coffee>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCoffeeByDate(): Query<List<Coffee>> {
        TODO("Not yet implemented")
    }

    override suspend fun getCoffeeByLocation(): Query<List<Coffee>> {
        TODO("Not yet implemented")
    }
}