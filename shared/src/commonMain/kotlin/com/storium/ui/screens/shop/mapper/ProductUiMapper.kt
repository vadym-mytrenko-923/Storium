package com.storium.ui.screens.shop.mapper

import com.storium.domain.features.product.model.Product
import com.storium.ui.screens.product.details.mapper.toRatingUiModel
import com.storium.ui.screens.shop.model.ProductUiModel

fun List<Product>.toUiModels(): List<ProductUiModel> = map { it.toUiModel() }

fun Product.toUiModel(): ProductUiModel = ProductUiModel(
    id = id,
    title = title,
    brand = brand,
    thumbnail = thumbnail,
    priceInfo = toPriceUiModel(price = price, discountPercentage = discountPercentage),
    rating = toRatingUiModel(reviews = reviews),
)
