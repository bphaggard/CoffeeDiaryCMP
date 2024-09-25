package org.example.project.coffeecmp.data.local

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import org.example.project.coffeecmp.db.CoffeeDatabase

actual class DatabaseDriverFactory(private val context: Context) {

    actual fun createDriver(): SqlDriver {
        return AndroidSqliteDriver(
            schema = CoffeeDatabase.Schema,
            context = context,
            name = "Coffee.Database.db"
        )
    }
}