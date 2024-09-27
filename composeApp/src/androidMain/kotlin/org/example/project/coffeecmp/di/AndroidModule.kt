package org.example.project.coffeecmp.di

import org.example.project.coffeecmp.CoffeeApp
import org.example.project.coffeecmp.data.local.DatabaseDriverFactory
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

actual fun initKoin() {
    startKoin {
        androidContext(CoffeeApp.instance)
        modules(appModule + module {
            single { DatabaseDriverFactory(androidContext()) }
        })
    }
}