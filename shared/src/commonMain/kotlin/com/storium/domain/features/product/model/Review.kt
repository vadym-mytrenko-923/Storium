package com.storium.domain.features.product.model

data class Review(
    val rating: Int,
    val comment: String,
    val reviewerName: String,
    val date: String,
)
