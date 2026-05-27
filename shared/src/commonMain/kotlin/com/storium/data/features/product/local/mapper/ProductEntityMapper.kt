package com.storium.data.features.product.local.mapper

import com.storium.data.features.product.local.entity.ProductEntity
import com.storium.data.features.product.local.entity.ProductWithReviews
import com.storium.domain.features.product.model.Product
import kotlinx.serialization.json.Json

fun List<ProductWithReviews>.toDomainModels(): List<Product> = map { it.toDomainModel() }

fun List<Product>.toEntities(): List<ProductEntity> = map { it.toEntity() }

fun ProductWithReviews.toDomainModel(): Product = Product(
    id = product.id,
    title = product.title,
    description = product.description,
    price = product.price,
    discountPercentage = product.discountPercentage,
    brand = product.brand,
    thumbnail = product.thumbnail,
    images = Json.decodeFromString(product.images),
    categoryId = product.categoryId,
    reviews = reviews.toDomainModels(),
)

fun Product.toEntity(): ProductEntity = ProductEntity(
    id = id,
    title = title,
    description = description,
    price = price,
    discountPercentage = discountPercentage,
    brand = brand,
    thumbnail = thumbnail,
    images = Json.encodeToString(images),
    categoryId = categoryId,
)
