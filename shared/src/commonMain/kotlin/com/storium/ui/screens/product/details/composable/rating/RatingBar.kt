package com.storium.ui.screens.product.details.composable.rating

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.product.details.composable.preview.ProductDetailsPreviewUiModels
import com.storium.ui.screens.product.details.model.RatingDistributionUiModel
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.ratingBarHeight
import com.storium.ui.theme.ratingBarShape
import com.storium.ui.theme.ratingStarSize
import org.jetbrains.compose.resources.painterResource

internal const val MAX_STARS = 5

@Composable
fun RatingBar(
    modifier: Modifier = Modifier,
    item: RatingDistributionUiModel,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Row(
            modifier = Modifier.width(ratingStarSize * MAX_STARS),
            horizontalArrangement = Arrangement.End,
        ) {
            repeat(item.stars) {
                Image(
                    modifier = Modifier.size(ratingStarSize),
                    painter = painterResource(AppIcons.StarFilled),
                    contentDescription = null,
                )
            }
        }

        Box(
            modifier = Modifier
                .weight(1f)
                .padding(start = marginPrimary)
                .height(ratingBarHeight)
                .clip(ratingBarShape),
        ) {
            val barModifier = if (item.reviewsFraction > 0f) {
                Modifier
                    .fillMaxWidth(item.reviewsFraction)
                    .height(ratingBarHeight)
            } else {
                Modifier.size(ratingBarHeight)
            }

            Box(
                modifier = barModifier
                    .clip(ratingBarShape)
                    .background(MaterialTheme.appColors.primary),
            )
        }

        Text(
            modifier = Modifier.padding(start = marginPrimary2X),
            text = item.reviewsNumber.toString(),
            style = MaterialTheme.typography.bodySmall,
            textAlign = TextAlign.End,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingBarPreview() {
    StoriumTheme {
        RatingBar(item = ProductDetailsPreviewUiModels.ratingDistributionItem)
    }
}

@Preview(showBackground = true)
@Composable
private fun RatingBarEmptyPreview() {
    StoriumTheme {
        RatingBar(item = ProductDetailsPreviewUiModels.ratingDistributionItemEmpty)
    }
}
