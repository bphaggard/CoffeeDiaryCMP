package org.example.project.coffeecmp.di

import org.example.project.coffeecmp.data.local.DatabaseDriverFactory
import org.koin.core.context.startKoin
import org.koin.dsl.module

actual fun initKoin() {
    startKoin {
        // Add the shared module and platform-specific module
        modules(appModule + module {
            single { DatabaseDriverFactory() } // Provide the iOS-specific driver factory
        })
    }
}