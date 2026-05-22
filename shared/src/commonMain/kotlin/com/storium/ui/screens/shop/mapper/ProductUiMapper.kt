package com.storium.ui.screens.shop.mapper

import com.storium.domain.features.product.model.Product
import com.storium.ui.screens.shop.model.ProductUiModel
import kotlin.math.roundToInt

fun List<Product>.toUiModels(): List<ProductUiModel> = map { it.toUiModel() }

fun Product.toUiModel(): ProductUiModel {
    val hasDiscount = discountPercentage > 0
    val discountedPrice = if (hasDiscount) price * (1 - discountPercentage / 100) else price

    return ProductUiModel(
        id = id,
        title = title,
        brand = brand,
        thumbnail = thumbnail,
        price = discountedPrice.roundToInt(),
        oldPrice = if (hasDiscount) price.roundToInt() else null,
        discountPercent = if (hasDiscount) discountPercentage.roundToInt() else null,
        rating = rating,
        reviewCount = reviewCount,
    )
}
