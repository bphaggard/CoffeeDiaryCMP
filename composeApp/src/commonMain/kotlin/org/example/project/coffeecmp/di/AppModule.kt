package org.example.project.coffeecmp.di

import app.cash.sqldelight.db.SqlDriver
import org.example.project.coffeecmp.CoffeeViewModel
import org.example.project.coffeecmp.data.local.DatabaseDriverFactory
import org.example.project.coffeecmp.db.CoffeeDatabase
import org.example.project.coffeecmp.domain.CoffeeDataSource
import org.example.project.coffeecmp.domain.CoffeeDataSourceImpl
import org.koin.compose.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

val appModule = module {
    single<SqlDriver> { get<DatabaseDriverFactory>().createDriver() }
    single<CoffeeDataSource> { CoffeeDataSourceImpl(get()) }
    single { CoffeeDatabase(get()) }
    viewModel { CoffeeViewModel(get()) }
}

fun initKoin() {
    startKoin {
        modules(appModule)
    }
}