package com.storium.ui.screens.product.details.model

import com.storium.ui.screens.shop.model.PriceUiModel

data class ProductDetailsUiModel(
    val id: Int,
    val title: String,
    val brand: String,
    val description: String,
    val images: List<String>,
    val priceInfo: PriceUiModel,
    val rating: RatingUiModel,
    val reviews: List<ReviewUiModel>,
)
