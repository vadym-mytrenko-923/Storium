package com.storium.ui.screens.shop

import com.storium.ui.screens.shop.model.CategoryUiModel
import com.storium.ui.screens.shop.model.DisplayMode
import com.storium.ui.screens.shop.model.ProductUiModel

data class ShopScreenState(
    val isLoading: Boolean = true,
    val products: List<ProductUiModel> = emptyList(),
    val categories: List<CategoryUiModel> = emptyList(),
    val displayMode: DisplayMode = DisplayMode.List,
    val isSearchActive: Boolean = false,
    val searchQuery: String = "",
)

sealed interface ShopIntent {
    data class CategoryToggled(val categoryId: String) : ShopIntent
    data object DisplayModeToggled : ShopIntent
    data class ProductClicked(val productId: Int) : ShopIntent
    data object SearchToggled : ShopIntent
    data class SearchQueryChanged(val query: String) : ShopIntent
}

sealed interface ShopEffect
