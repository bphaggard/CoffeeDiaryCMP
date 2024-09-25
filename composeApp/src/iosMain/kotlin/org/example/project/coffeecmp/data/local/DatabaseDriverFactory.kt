package org.example.project.coffeecmp.data.local

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import org.example.project.coffeecmp.db.CoffeeDatabase

actual class DatabaseDriverFactory() {

    actual fun createDriver(): SqlDriver {
        return NativeSqliteDriver(
            schema = CoffeeDatabase.Schema,
            name = "CoffeeDatabase.db"
        )
    }
}