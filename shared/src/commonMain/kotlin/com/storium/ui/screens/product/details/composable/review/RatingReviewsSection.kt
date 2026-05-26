package com.storium.ui.screens.product.details.composable.review

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.product.details.composable.preview.ProductDetailsPreviewUiModels
import com.storium.ui.screens.product.details.composable.rating.RatingSummary
import com.storium.ui.screens.product.details.model.RatingUiModel
import com.storium.ui.screens.product.details.model.ReviewUiModel
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary2X
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.productDetailsRatingTitle
import storium.shared.generated.resources.productDetailsReviewsCountFormat

@Composable
fun RatingReviewsSection(
    modifier: Modifier = Modifier,
    rating: RatingUiModel,
    reviews: List<ReviewUiModel>,
) {
    if (reviews.isEmpty()) return

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = marginPrimary2X),
        verticalArrangement = Arrangement.spacedBy(marginPrimary2X),
    ) {
        Text(
            text = stringResource(Res.string.productDetailsRatingTitle),
            style = MaterialTheme.typography.headlineLarge,
        )

        Spacer(modifier = Modifier.height(marginPrimary2X))

        RatingSummary(rating = rating)

        Spacer(modifier = Modifier.height(marginPrimary2X))

        Text(
            text = stringResource(Res.string.productDetailsReviewsCountFormat, reviews.size),
            style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.appColors.textPrimary,
        )

        reviews.forEach { review -> ReviewCard(review = review, maxStars = rating.maxStars) }
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingReviewsSectionPreview() {
    StoriumTheme {
        RatingReviewsSection(
            rating = ProductDetailsPreviewUiModels.rating,
            reviews = ProductDetailsPreviewUiModels.reviews,
        )
    }
}
