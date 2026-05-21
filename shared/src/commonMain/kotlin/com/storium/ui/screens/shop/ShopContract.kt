package com.storium.ui.screens.shop

import com.storium.domain.features.product.model.Category
import com.storium.domain.features.product.model.Product
import com.storium.ui.screens.shop.model.CategoryUiModel
import com.storium.ui.screens.shop.model.DisplayMode
import com.storium.ui.screens.shop.model.ProductUiModel

data class ShopScreenState(
    val isLoading: Boolean = true,
    val allProducts: List<Product> = emptyList(),
    val products: List<ProductUiModel> = emptyList(),
    val categories: List<CategoryUiModel> = emptyList(),
    val selectedCategories: List<Category> = emptyList(),
    val displayMode: DisplayMode = DisplayMode.List,
)

sealed interface ShopIntent {
    data class CategoryToggled(val category: Category) : ShopIntent
    data object DisplayModeToggled : ShopIntent
    data class ProductClicked(val productId: Int) : ShopIntent
}

sealed interface ShopEffect
