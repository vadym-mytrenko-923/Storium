package com.storium.domain.features.product

import com.storium.domain.features.product.model.Product
import com.storium.domain.features.product.model.ProductsDataState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val productsFlow: Flow<ProductsDataState>
    val selectedCategoryIdsFlow: Flow<Set<String>>
    fun toggleCategorySelection(categoryId: String)
    suspend fun getProductById(id: Int): Product
    suspend fun fetchProducts()
    suspend fun fetchProductsByCategory(id: String)
}
