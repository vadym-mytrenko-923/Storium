package com.storium.ui.screens.shop.composable.list

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.storium.ui.theme.imageShapeStartOnly
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary1_5X
import com.storium.ui.theme.marginPrimaryHalf
import com.storium.ui.theme.productListImageWidth

@Composable
fun ProductListItem(
    modifier: Modifier = Modifier,
    product: ProductUiModel,
    onClick: () -> Unit,
) {
    ElevatedSurface(shape = containerShapeDefault) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(productListImageWidth)
                .clickable { onClick() },
        ) {
            Box {
                AsyncImage(
                    model = product.thumbnail,
                    contentDescription = product.title,
                    modifier = Modifier
                        .width(productListImageWidth)
                        .fillMaxHeight()
                        .clip(imageShapeStartOnly)
                        .background(MaterialTheme.appColors.surface),
                    contentScale = ContentScale.Crop,
                )

                (product.priceInfo as? PriceUiModel.Discounted)?.discountPercent?.let {
                    DiscountChip(
                        modifier = Modifier
                            .align(Alignment.TopStart)
                            .padding(marginPrimary),
                        discountPercent = it,
                    )
                }
            }

            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .padding(marginPrimary1_5X),
            ) {
                Text(
                    text = product.title,
                    style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.Medium),
                    color = MaterialTheme.appColors.textPrimary,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )

                if (product.brand.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(marginPrimaryHalf))

                    Text(
                        text = product.brand,
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.appColors.textSecondary,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                }

                Spacer(modifier = Modifier.height(marginPrimary))

                SmallRatingBar(rating = product.rating)

                Spacer(modifier = Modifier.height(marginPrimary))

                ProductPriceRow(priceInfo = product.priceInfo)
            }
        }
    }
}

@Preview
@Composable
private fun ProductListItemPreview() {
    StoriumTheme {
        ProductListItem(
            product = ShopPreviewUiModels.product,
            onClick = {},
        )
    }
}

@Preview
@Composable
private fun ProductListItemDiscountPreview() {
    StoriumTheme {
        ProductListItem(
            product = ShopPreviewUiModels.productWithDiscount,
            onClick = {},
        )
    }
}
