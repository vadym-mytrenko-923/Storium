package com.storium.data.features.product.remote.api

import com.storium.data.features.product.remote.model.CategoryDto
import com.storium.data.features.product.remote.model.ProductsResponseDto

interface ProductApi {
    suspend fun getProducts(): ProductsResponseDto
    suspend fun getCategories(): List<CategoryDto>
    suspend fun getProductsByCategory(id: String): ProductsResponseDto
}
