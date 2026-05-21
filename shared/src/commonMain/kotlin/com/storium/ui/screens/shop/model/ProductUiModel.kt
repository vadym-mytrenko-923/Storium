package com.storium.ui.screens.shop.model

data class ProductUiModel(
    val id: Int,
    val title: String,
    val brand: String,
    val thumbnail: String,
    val price: Int,
    val oldPrice: Int?,
    val discountPercent: Int?,
    val rating: Double,
    val reviewCount: Int,
)
