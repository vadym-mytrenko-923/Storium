package com.storium.ui.screens.shop.mapper

import com.storium.domain.features.product.model.Product
import com.storium.ui.screens.shop.model.ProductUiModel

fun List<Product>.toUiModels(): List<ProductUiModel> = map { it.toUiModel() }

fun Product.toUiModel(): ProductUiModel = ProductUiModel(
    id = id,
    title = title,
    brand = brand,
    thumbnail = thumbnail,
    priceInfo = toPriceUiModel(price, discountPercentage),
    rating = if (reviews.isNotEmpty()) reviews.map { it.rating.toDouble() }.average() else 0.0,
    reviewCount = reviews.size,
)
