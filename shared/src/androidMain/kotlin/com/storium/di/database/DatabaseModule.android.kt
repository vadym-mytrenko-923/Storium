package com.storium.di.database

import com.storium.data.features.product.local.db.ProductsDatabaseFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

actual val databaseModule = module {
    single { ProductsDatabaseFactory(androidContext()).create() }
}
