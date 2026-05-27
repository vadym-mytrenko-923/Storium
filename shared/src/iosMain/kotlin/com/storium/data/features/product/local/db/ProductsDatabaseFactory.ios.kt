package com.storium.data.features.product.local.db

import androidx.room.Room
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

@OptIn(kotlinx.cinterop.ExperimentalForeignApi::class)
actual class ProductsDatabaseFactory {
    actual fun create(): ProductsDatabase {
        val documentDirectory = NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )

        val dbPath = requireNotNull(documentDirectory).path + "/$DB_NAME"

        return Room
            .databaseBuilder<ProductsDatabase>(name = dbPath)
            .setDriver(BundledSQLiteDriver())
            .build()
    }

    companion object {
        private const val DB_NAME = "products.db"
    }
}
