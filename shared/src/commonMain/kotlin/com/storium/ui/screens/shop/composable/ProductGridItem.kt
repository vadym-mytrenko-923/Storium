package com.storium.ui.screens.shop.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.storium.ui.screens.shop.composable.preview.ShopPreviewUiModels
import com.storium.ui.screens.shop.model.ProductUiModel
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.containerShapeDefault
import com.storium.ui.theme.elevationCard
import com.storium.ui.theme.imageShapeDefault
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimaryHalf
import com.storium.ui.theme.productCardImageHeight
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopPriceFormat

@Composable
fun ProductGridItem(
    product: ProductUiModel,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .clip(containerShapeDefault)
            .clickable { onClick() },
    ) {
        Box {
            AsyncImage(
                model = product.thumbnail,
                contentDescription = product.title,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(productCardImageHeight)
                    .shadow(elevation = elevationCard, shape = imageShapeDefault)
                    .clip(imageShapeDefault)
                    .background(MaterialTheme.appColors.surface),
                contentScale = ContentScale.Crop,
            )

            product.discountPercent?.let {
                DiscountChip(
                    discountPercent = it,
                    modifier = Modifier
                        .align(Alignment.TopStart)
                        .padding(marginPrimary),
                )
            }
        }

        Spacer(modifier = Modifier.height(marginPrimary))

        SmallRatingBar(
            rating = product.rating,
            reviewCount = product.reviewCount,
        )

        Spacer(modifier = Modifier.height(marginPrimaryHalf + marginPrimaryHalf / 2))

        Text(
            text = product.brand,
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.appColors.textSecondary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(marginPrimaryHalf))

        Text(
            text = product.title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.appColors.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(marginPrimaryHalf))

        Row(horizontalArrangement = Arrangement.spacedBy(marginPrimaryHalf)) {
            if (product.oldPrice != null) {
                Text(
                    text = stringResource(Res.string.shopPriceFormat, product.oldPrice),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.appColors.textSecondary,
                    textDecoration = TextDecoration.LineThrough,
                )

                Text(
                    text = stringResource(Res.string.shopPriceFormat, product.price),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.appColors.primary,
                )
            } else {
                Text(
                    text = stringResource(Res.string.shopPriceFormat, product.price),
                    style = MaterialTheme.typography.labelLarge,
                    color = MaterialTheme.appColors.textPrimary,
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductGridItemPreview() {
    StoriumTheme {
        ProductGridItem(
            product = ShopPreviewUiModels.productWithDiscount,
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductGridItemNoPricePreview() {
    StoriumTheme {
        ProductGridItem(
            product = ShopPreviewUiModels.product,
            onClick = {},
        )
    }
}
