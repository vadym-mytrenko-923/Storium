package com.storium.data.features.product.local.source

import com.storium.domain.features.product.model.Category
import com.storium.domain.features.product.model.Product
import kotlinx.coroutines.flow.Flow

interface ProductsLocalDataSource {
    fun getProductsFlow(categoryIds: List<String>, query: String): Flow<List<Product>>
    fun getAllCategoriesFlow(): Flow<List<Category>>
    suspend fun getAllCategories(): List<Category>
    suspend fun getProductById(id: Int): Product?
    suspend fun getProductsCount(): Int
    suspend fun saveAll(products: List<Product>, categories: List<Category>)
}
