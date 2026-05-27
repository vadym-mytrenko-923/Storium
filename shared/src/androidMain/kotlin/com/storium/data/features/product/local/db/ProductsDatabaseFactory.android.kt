package com.storium.data.features.product.local.db

import android.content.Context
import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver

actual class ProductsDatabaseFactory(private val context: Context) {
    actual fun create(): ProductsDatabase = Room
        .databaseBuilder<ProductsDatabase>(context = context, name = context.getDatabasePath(DB_NAME).absolutePath)
        .setDriver(BundledSQLiteDriver())
        .build()

    companion object {
        private const val DB_NAME = "products.db"
    }
}
