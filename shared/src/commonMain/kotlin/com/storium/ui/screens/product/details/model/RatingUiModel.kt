package com.storium.ui.screens.product.details.model

data class RatingUiModel(
    val displayRating: String,
    val filledStars: Int,
    val maxStars: Int,
    val totalCount: Int,
    val distribution: List<RatingDistributionUiModel>,
)
