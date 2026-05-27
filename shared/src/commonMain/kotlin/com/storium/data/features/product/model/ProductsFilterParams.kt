package com.storium.data.features.product.model

import com.storium.domain.features.product.model.Category

data class ProductsFilterParams(
    val selectedCategoryIds: List<String>,
    val query: String,
    val categories: List<Category>,
    val isLoading: Boolean,
)
