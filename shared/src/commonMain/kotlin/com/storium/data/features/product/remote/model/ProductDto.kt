package com.storium.data.features.product.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class ProductDto(
    val id: Int,
    val title: String,
    val price: Double,
    val discountPercentage: Double,
    val rating: Double,
    val brand: String? = null,
    val thumbnail: String,
    val category: String,
    val reviews: List<ReviewDto> = emptyList(),
)
