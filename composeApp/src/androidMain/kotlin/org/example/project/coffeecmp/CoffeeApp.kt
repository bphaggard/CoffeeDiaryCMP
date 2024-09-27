package org.example.project.coffeecmp

import android.app.Application
import org.example.project.coffeecmp.data.local.DatabaseDriverFactory
import org.example.project.coffeecmp.di.appModule
import org.example.project.coffeecmp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

//class CoffeeApp: Application() {
//
//    override fun onCreate() {
//        super.onCreate()
//        initKoin()
//    }
//
////    private fun initKoin() {
////        startKoin {
////            // Provide the Android context for Koin
////            androidContext(this@CoffeeApp)
////
////            // Provide the platform-specific module, including DatabaseDriverFactory
////            modules(appModule + module {
////                single { DatabaseDriverFactory(androidContext()) }
////            })
////        }
////    }
//}

class CoffeeApp : Application() {
    companion object {
        lateinit var instance: CoffeeApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        instance = this // Set the instance
        initKoin() // Initialize Koin
    }
}
