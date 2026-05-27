package com.storium.data.features.product.local.db

expect class ProductsDatabaseFactory {
    fun create(): ProductsDatabase
}
