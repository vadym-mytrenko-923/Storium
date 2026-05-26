package com.storium.data.features.product.mapper

import com.storium.data.features.product.remote.model.ReviewDto
import com.storium.domain.features.product.model.Review

fun List<ReviewDto>.toDomainModels(): List<Review> = map { it.toDomainModel() }

fun ReviewDto.toDomainModel(): Review = Review(
    rating = rating,
    comment = comment,
    reviewerName = reviewerName,
    date = date,
)
