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
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimaryQuarter
import com.storium.ui.theme.ratingStarSize
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopRatingCount

private const val DEFAULT_MAX_STARS = 5

@Composable
fun SmallRatingBar(
    modifier: Modifier = Modifier,
    rating: Double,
    reviewCount: Int,
    maxStars: Int = DEFAULT_MAX_STARS,
) {
    Row(
        modifier = modifier.height(ratingStarSize),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(maxStars) { index ->
            Image(
                modifier = Modifier.size(ratingStarSize),
                painter = painterResource(
                    if (index < rating.toInt()) AppIcons.StarFilled else AppIcons.StarEmpty,
                ),
                contentDescription = null,
            )
        }

        Spacer(modifier = Modifier.width(marginPrimaryQuarter))

        Text(
            text = stringResource(Res.string.shopRatingCount, reviewCount),
            style = MaterialTheme.typography.bodySmall.copy(
                color = MaterialTheme.appColors.textSecondary,
            )
        )
    }
}

@Preview
@Composable
private fun SmallRatingBarPreview() {
    StoriumTheme {
        SmallRatingBar(rating = 4.2, reviewCount = 3)
    }
}

@Preview
@Composable
private fun SmallRatingBarEmptyPreview() {
    StoriumTheme {
        SmallRatingBar(rating = 0.0, reviewCount = 0)
    }
}
