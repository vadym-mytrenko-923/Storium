package com.storium.ui.screens.product.details.composable.rating

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.product.details.composable.preview.ProductDetailsPreviewUiModels
import com.storium.ui.screens.product.details.model.RatingUiModel
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary3X
import com.storium.ui.theme.marginPrimaryHalf
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.productDetailsRatingsCountFormat

@Composable
fun RatingSummary(
    modifier: Modifier = Modifier,
    rating: RatingUiModel,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = rating.displayRating,
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.appColors.textPrimary,
            )

            Text(
                text = stringResource(Res.string.productDetailsRatingsCountFormat, rating.totalCount),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.appColors.textSecondary,
            )
        }

        Spacer(modifier = Modifier.width(marginPrimary3X))

        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(marginPrimaryHalf),
        ) {
            rating.distribution.forEach { item ->
                RatingBar(item = item, maxStars = rating.maxStars)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingSummaryPreview() {
    StoriumTheme {
        RatingSummary(rating = ProductDetailsPreviewUiModels.rating)
    }
}
