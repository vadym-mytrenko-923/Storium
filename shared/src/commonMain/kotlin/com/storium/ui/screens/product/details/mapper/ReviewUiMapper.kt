package com.storium.ui.screens.product.details.mapper

import com.storium.domain.features.product.model.Review
import com.storium.ui.screens.product.details.model.ReviewUiModel
import com.storium.util.date.DateFormatter

class ReviewUiMapper(private val dateFormatter: DateFormatter) {
    fun map(reviews: List<Review>): List<ReviewUiModel> = reviews.map { map(it) }

    fun map(review: Review): ReviewUiModel = ReviewUiModel(
        rating = review.rating,
        comment = review.comment,
        reviewerName = review.reviewerName,
        date = dateFormatter.format(review.date),
    )
}
