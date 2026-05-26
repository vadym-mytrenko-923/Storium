package com.storium.data.features.product.mapper

import com.storium.data.features.product.remote.model.ProductDto
import com.storium.domain.features.product.model.Product

fun List<ProductDto>.toDomainModels(): List<Product> = map { it.toDomainModel() }

fun ProductDto.toDomainModel(): Product = Product(
    id = id,
    title = title,
    price = price,
    discountPercentage = discountPercentage,
    brand = brand.orEmpty(),
    thumbnail = thumbnail,
    images = images,
    description = description,
    categoryId = category,
    reviews = reviews.toDomainModels(),
)
