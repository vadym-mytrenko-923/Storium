package com.storium.ui.screens.shop.composable.grid

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.storium.ui.core.composable.surface.ElevatedSurface
import com.storium.ui.screens.shop.composable.common.DiscountChip
import com.storium.ui.screens.shop.composable.common.SmallRatingBar
import com.storium.ui.screens.shop.composable.common.price.ProductPriceRow
import com.storium.ui.screens.shop.composable.preview.ShopPreviewUiModels
import com.storium.ui.screens.shop.model.PriceUiModel
import com.storium.ui.screens.shop.model.ProductUiModel
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.containerShapeDefault
import com.storium.ui.theme.imageShapeDefault
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary0_75X
import com.storium.ui.theme.marginPrimaryHalf
import com.storium.ui.theme.productCardImageHeight

@Composable
fun ProductGridItem(
    modifier: Modifier = Modifier,
    product: ProductUiModel,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .clip(containerShapeDefault)
            .clickable { onClick() },
    ) {
        ElevatedSurface(shape = imageShapeDefault) {
            Box {
                AsyncImage(
                    modifier = Modifier.height(productCardImageHeight),
                    model = product.thumbnail,
                    contentDescription = product.title,
                    contentScale = ContentScale.Crop,
                )

                if (product.priceInfo is PriceUiModel.Discounted) {
                    DiscountChip(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(marginPrimary),
                        discountPercent = product.priceInfo.discountPercent,
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(marginPrimary))

        SmallRatingBar(rating = product.rating)

        Spacer(modifier = Modifier.height(marginPrimary0_75X))

        if (product.brand.isNotEmpty()) {
            Text(
                text = product.brand,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.appColors.textSecondary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            Spacer(modifier = Modifier.height(marginPrimaryHalf))
        }

        Text(
            text = product.title,
            style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
            color = MaterialTheme.appColors.textPrimary,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
        )

        Spacer(modifier = Modifier.height(marginPrimaryHalf))

        ProductPriceRow(priceInfo = product.priceInfo)
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
