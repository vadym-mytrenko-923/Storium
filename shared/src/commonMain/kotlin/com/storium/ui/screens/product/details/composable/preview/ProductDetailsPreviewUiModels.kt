package com.storium.ui.screens.product.details.composable.preview

import com.storium.ui.core.error.model.AppStringResource
import com.storium.ui.screens.product.details.model.ProductDetailsUiModel
import com.storium.ui.screens.product.details.model.RatingDistributionUiModel
import com.storium.ui.screens.product.details.model.RatingUiModel
import com.storium.ui.screens.product.details.model.ReviewUiModel
import com.storium.ui.screens.shop.model.PriceUiModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopPriceFormat

object ProductDetailsPreviewUiModels {
    // Reviews
    val reviews = listOf(
        ReviewUiModel(
            rating = 4,
            comment = "The dress is great! Very classy and comfortable. It fit perfectly!",
            reviewerName = "Helene Moore",
            date = "Jun 5, 2024",
        ),
        ReviewUiModel(
            rating = 5,
            comment = "Awesome product! Highly recommended.",
            reviewerName = "Kate Doe",
            date = "May 20, 2024",
        ),
        ReviewUiModel(
            rating = 3,
            comment = "Good quality but the color was slightly different from the picture.",
            reviewerName = "Xavier Wright",
            date = "Apr 15, 2024",
        ),
    )

    // Rating
    val ratingDistributionItem = RatingDistributionUiModel(stars = 4, reviewsNumber = 1, reviewsFraction = 1f / 3)
    val ratingDistributionItemEmpty = RatingDistributionUiModel(stars = 1, reviewsNumber = 0, reviewsFraction = 0f)
    val rating = RatingUiModel(
        displayRating = "4.0",
        filledStars = 4,
        maxStars = 5,
        totalCount = reviews.size,
        distribution = listOf(
            RatingDistributionUiModel(stars = 5, reviewsNumber = 1, reviewsFraction = 1f / 3),
            RatingDistributionUiModel(stars = 4, reviewsNumber = 1, reviewsFraction = 1f / 3),
            RatingDistributionUiModel(stars = 3, reviewsNumber = 1, reviewsFraction = 1f / 3),
            RatingDistributionUiModel(stars = 2, reviewsNumber = 0, reviewsFraction = 0f),
            RatingDistributionUiModel(stars = 1, reviewsNumber = 0, reviewsFraction = 0f),
        ),
    )

    // Products
    val product = ProductDetailsUiModel(
        id = 1,
        title = "iPhone 9",
        brand = "Apple",
        description = "An apple mobile which is nothing like apple. " +
            "A detailed description of the product goes here with multiple lines of text.",
        images = listOf("", "", ""),
        priceInfo = PriceUiModel.Regular(
            price = AppStringResource(Res.string.shopPriceFormat, listOf("449.00")),
        ),
        rating = rating,
        reviews = reviews,
    )
    val productWithDiscount = product.copy(
        id = 2,
        priceInfo = PriceUiModel.Discounted(
            price = AppStringResource(Res.string.shopPriceFormat, listOf("449.00")),
            oldPrice = AppStringResource(Res.string.shopPriceFormat, listOf("549.00")),
            discountPercent = 18,
        ),
    )
}
