package com.storium.data.features.product.remote.source

import com.storium.data.features.product.remote.api.ProductApi
import com.storium.data.features.product.remote.model.CategoryDto
import com.storium.data.features.product.remote.model.ProductDto

class ProductRemoteDataSourceImpl(private val productApi: ProductApi) : ProductRemoteDataSource {
    override suspend fun getProducts(): List<ProductDto> = productApi.getProducts().products

    override suspend fun getCategories(): List<CategoryDto> = productApi.getCategories()

    override suspend fun getProductsByCategory(id: String): List<ProductDto> {
        return productApi.getProductsByCategory(id).products
    }
}
