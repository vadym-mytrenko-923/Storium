package com.storium.data.features.product.remote.api

import com.storium.data.features.product.remote.model.CategoryDto
import com.storium.data.features.product.remote.model.ProductDto
import com.storium.data.features.product.remote.model.ProductsResponseDto
import com.storium.data.remote.getRequest
import io.ktor.client.HttpClient

class ProductApiImpl(private val httpClient: HttpClient) : ProductApi {
    override suspend fun getProducts(): ProductsResponseDto = httpClient.getRequest("/products?limit=0")

    override suspend fun getProductById(id: Int): ProductDto = httpClient.getRequest("/products/$id")

    override suspend fun getCategories(): List<CategoryDto> = httpClient.getRequest("/products/categories")

    override suspend fun getProductsByCategory(id: String): ProductsResponseDto {
        return httpClient.getRequest("/products/category/$id")
    }
}
