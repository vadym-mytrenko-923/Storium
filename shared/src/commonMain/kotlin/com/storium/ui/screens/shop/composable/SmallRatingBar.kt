package com.storium.ui.screens.shop.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import com.storium.ui.theme.textSizeTiny
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopRatingCount

private const val DEFAULT_MAX_STARS = 5

@Composable
fun SmallRatingBar(
    rating: Double,
    reviewCount: Int,
    modifier: Modifier = Modifier,
    maxStars: Int = DEFAULT_MAX_STARS,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        repeat(maxStars) { index ->
            Image(
                painter = painterResource(
                    if (index < rating.toInt()) AppIcons.StarFilled else AppIcons.StarEmpty,
                ),
                contentDescription = null,
                modifier = Modifier.size(ratingStarSize),
            )
        }

        Spacer(modifier = Modifier.width(marginPrimaryQuarter))

        Text(
            text = stringResource(Res.string.shopRatingCount, reviewCount),
            fontSize = textSizeTiny,
            color = MaterialTheme.appColors.textSecondary,
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
