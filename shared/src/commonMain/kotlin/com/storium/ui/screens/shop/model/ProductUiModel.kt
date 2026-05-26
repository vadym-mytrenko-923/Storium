package com.storium.ui.screens.shop.model

data class ProductUiModel(
    val id: Int,
    val title: String,
    val brand: String,
    val thumbnail: String,
    val priceInfo: PriceUiModel,
    val rating: Double,
    val reviewCount: Int,
)
