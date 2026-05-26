package com.storium.ui.screens.product.details.mapper

import com.storium.domain.features.product.model.Product
import com.storium.ui.screens.product.details.model.ProductDetailsUiModel
import com.storium.ui.screens.shop.mapper.toPriceUiModel

class ProductDetailsUiMapper(private val reviewUiMapper: ReviewUiMapper) {
    fun map(product: Product): ProductDetailsUiModel = ProductDetailsUiModel(
        id = product.id,
        title = product.title,
        brand = product.brand,
        description = product.description,
        images = product.images.ifEmpty { listOf(product.thumbnail) },
        priceInfo = toPriceUiModel(product.price, product.discountPercentage),
        rating = product.toRatingUiModel(),
        reviews = reviewUiMapper.map(product.reviews),
    )
}
