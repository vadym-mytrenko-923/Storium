package com.storium.domain.features.product

import com.storium.domain.features.product.model.Product
import com.storium.domain.features.product.model.ProductsDataState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val selectedCategoryIdsFlow: Flow<List<String>>
    val searchQueryFlow: Flow<String>
    val productsFlow: Flow<ProductsDataState>
    fun toggleCategorySelection(categoryId: String)
    fun setSearchQuery(query: String)
    suspend fun getProductById(id: Int): Product
    suspend fun fetchProducts()
}
