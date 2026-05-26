package com.storium.ui.screens.shop.composable.common

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.product.details.composable.preview.ProductDetailsPreviewUiModels
import com.storium.ui.screens.product.details.model.RatingUiModel
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimaryQuarter
import com.storium.ui.theme.ratingStarSize
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopRatingCountFormat

@Composable
fun SmallRatingBar(
    modifier: Modifier = Modifier,
    rating: RatingUiModel,
) {
    Row(
        modifier = modifier.height(ratingStarSize),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(rating.maxStars) { index ->
            Image(
                modifier = Modifier.size(ratingStarSize),
                painter = painterResource(
                    if (index < rating.filledStars) AppIcons.StarFilled else AppIcons.StarEmpty,
                ),
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.width(marginPrimaryQuarter))

        Text(
            text = stringResource(Res.string.shopRatingCountFormat, rating.totalCount),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.appColors.textSecondary,
            ),
        )
    }
}

@Preview
@Composable
private fun SmallRatingBarPreview() {
    StoriumTheme {
        SmallRatingBar(rating = ProductDetailsPreviewUiModels.rating)
    }
}
