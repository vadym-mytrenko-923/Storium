package com.storium.data.features.product.remote.source

import com.storium.data.features.product.remote.model.CategoryDto
import com.storium.data.features.product.remote.model.ProductDto

interface ProductRemoteDataSource {
    suspend fun getProducts(): List<ProductDto>
    suspend fun getCategories(): List<CategoryDto>
    suspend fun getProductsByCategory(id: String): List<ProductDto>
}
