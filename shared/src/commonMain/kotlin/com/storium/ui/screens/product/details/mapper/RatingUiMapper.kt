package com.storium.ui.screens.product.details.mapper

import com.storium.domain.features.product.model.Review
import com.storium.ui.screens.product.details.model.RatingDistributionUiModel
import com.storium.ui.screens.product.details.model.RatingUiModel
import com.storium.util.roundTo

private const val MAX_STARS = 5
private const val MIN_STARS = 1
private const val NO_RATING = 0.0
private const val RATING_DECIMAL_PLACES = 1
private const val NO_PROGRESS = 0f

fun toRatingUiModel(reviews: List<Review>): RatingUiModel {
    val totalCount = reviews.size
    val average = if (totalCount > 0) reviews.map { it.rating }.average() else NO_RATING

    return RatingUiModel(
        displayRating = average.roundTo(RATING_DECIMAL_PLACES).toString(),
        filledStars = average.toInt(),
        maxStars = MAX_STARS,
        totalCount = totalCount,
        distribution = (MAX_STARS downTo MIN_STARS).map { stars ->
            val count = reviews.count { it.rating == stars }

            RatingDistributionUiModel(
                stars = stars,
                reviewsNumber = count,
                reviewsFraction = if (totalCount > 0) count.toFloat() / totalCount else NO_PROGRESS,
            )
        },
    )
}
