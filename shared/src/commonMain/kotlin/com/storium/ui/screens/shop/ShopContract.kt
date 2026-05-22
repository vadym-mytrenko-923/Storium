package com.storium.ui.screens.shop

import com.storium.ui.screens.shop.model.CategoryUiModel
import com.storium.ui.screens.shop.model.DisplayMode
import com.storium.ui.screens.shop.model.ProductUiModel

data class ShopScreenState(
    val isLoading: Boolean = true,
    val products: List<ProductUiModel> = emptyList(),
    val categories: List<CategoryUiModel> = emptyList(),
    val displayMode: DisplayMode = DisplayMode.List,
)

sealed interface ShopIntent {
    data class CategoryToggled(val categoryId: String) : ShopIntent
    data object DisplayModeToggled : ShopIntent
    data class ProductClicked(val productId: Int) : ShopIntent
}

sealed interface ShopEffect
