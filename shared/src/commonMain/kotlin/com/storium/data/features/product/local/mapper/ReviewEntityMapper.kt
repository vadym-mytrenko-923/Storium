package com.storium.data.features.product.local.mapper

import com.storium.data.features.product.local.entity.ReviewEntity
import com.storium.domain.features.product.model.Review

fun List<ReviewEntity>.toDomainModels(): List<Review> = map { it.toDomainModel() }

fun ReviewEntity.toDomainModel(): Review = Review(
    rating = rating,
    comment = comment,
    reviewerName = reviewerName,
    date = date,
)

fun Review.toEntity(productId: Int): ReviewEntity = ReviewEntity(
    productId = productId,
    rating = rating,
    comment = comment,
    reviewerName = reviewerName,
    date = date,
)
