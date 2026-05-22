package com.storium.domain.features.product.model

data class ProductsDataState(
    val products: List<Product> = emptyList(),
    val categories: List<Category> = emptyList(),
    val isLoading: Boolean = false,
)
