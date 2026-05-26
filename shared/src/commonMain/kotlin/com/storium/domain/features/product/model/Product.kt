package com.storium.domain.features.product.model

data class Product(
    val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val brand: String,
    val thumbnail: String,
    val images: List<String>,
    val categoryId: String,
    val reviews: List<Review>,
)
