package com.storium.data.features.product.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ReviewDto(
    val rating: Int,
    val comment: String,
    val reviewerName: String,
    val date: String = "",
)
