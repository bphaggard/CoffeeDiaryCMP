package org.example.project.coffeecmp.di

import app.cash.sqldelight.db.SqlDriver
import org.example.project.coffeecmp.data.local.DatabaseDriverFactory
import org.example.project.coffeecmp.db.CoffeeDatabase
import org.koin.dsl.module

val databaseModule = module {
    single<SqlDriver> { DatabaseDriverFactory().createDriver() }
    single { CoffeeDatabase(get()) }
}