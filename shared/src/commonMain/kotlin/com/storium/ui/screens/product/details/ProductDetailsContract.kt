package com.storium.ui.screens.product.details

data class ProductDetailsScreenState(
    val productId: Int = 0,
)

sealed interface ProductDetailsIntent {
    data object BackClicked : ProductDetailsIntent
}

sealed interface ProductDetailsEffect
