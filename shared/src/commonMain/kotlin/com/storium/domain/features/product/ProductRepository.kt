package com.storium.domain.features.product

import com.storium.domain.features.product.model.ProductsDataState
import kotlinx.coroutines.flow.Flow

interface ProductRepository {
    val productsFlow: Flow<ProductsDataState>
    suspend fun fetchProducts()
    suspend fun fetchProductsByCategory(id: String)
}
