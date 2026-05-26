package com.storium.ui.screens.product.details.composable.review

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.storium.ui.screens.product.details.composable.preview.ProductDetailsPreviewUiModels
import com.storium.ui.screens.product.details.composable.rating.MAX_STARS
import com.storium.ui.screens.product.details.model.ReviewUiModel
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.containerShapeDefault
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.ratingStarSize
import org.jetbrains.compose.resources.painterResource

@Composable
fun ReviewCard(
    modifier: Modifier = Modifier,
    review: ReviewUiModel,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .clip(containerShapeDefault)
            .background(MaterialTheme.appColors.cardBackground)
            .padding(marginPrimary2X),
    ) {
        Text(
            text = review.reviewerName,
            style = MaterialTheme.typography.labelLarge.copy(fontWeight = FontWeight.Bold),
            color = MaterialTheme.appColors.textPrimary,
        )

        Spacer(modifier = Modifier.height(marginPrimary))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Row(horizontalArrangement = Arrangement.spacedBy(1.dp)) {
                repeat(MAX_STARS) { index ->
                    Image(
                        modifier = Modifier.size(ratingStarSize),
                        painter = painterResource(
                            if (index < review.rating) AppIcons.StarFilled else AppIcons.StarEmpty,
                        ),
                        contentDescription = null,
                    )
                }
            }

            Text(
                text = review.date,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.appColors.textSecondary,
            )
        }

        Spacer(modifier = Modifier.height(marginPrimary))

        Text(
            text = review.comment,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.appColors.textPrimary,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ReviewCardPreview() {
    StoriumTheme {
        ReviewCard(review = ProductDetailsPreviewUiModels.reviews.first())
    }
}
