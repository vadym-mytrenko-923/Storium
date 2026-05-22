package com.storium.data.features.product.mapper

import com.storium.data.features.product.remote.model.ProductDto
import com.storium.domain.features.product.model.Product

fun ProductDto.toDomainModel(): Product = Product(
    id = id,
    title = title,
    price = price,
    discountPercentage = discountPercentage,
    rating = rating,
    brand = brand.orEmpty(),
    thumbnail = thumbnail,
    category = category,
    reviewCount = reviews.size,
)
