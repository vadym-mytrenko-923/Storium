package com.storium.ui.screens.product.details

import com.storium.ui.screens.product.details.model.ProductDetailsUiModel

data class ProductDetailsScreenState(
    val isLoading: Boolean = true,
    val product: ProductDetailsUiModel? = null,
)

sealed interface ProductDetailsIntent {
    data object BackClicked : ProductDetailsIntent
}

sealed interface ProductDetailsEffect
