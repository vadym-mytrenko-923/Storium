package com.storium.ui.screens.shop.model

import com.storium.ui.screens.product.details.model.RatingUiModel

data class ProductUiModel(
    val id: Int,
    val title: String,
    val brand: String,
    val thumbnail: String,
    val priceInfo: PriceUiModel,
    val rating: RatingUiModel,
)
