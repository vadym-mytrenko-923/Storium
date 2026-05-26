package com.storium.ui.screens.product.details.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.product.details.composable.image.ImageCarousel
import com.storium.ui.screens.product.details.composable.preview.ProductDetailsPreviewUiModels
import com.storium.ui.screens.product.details.composable.review.RatingReviewsSection
import com.storium.ui.screens.product.details.model.ProductDetailsUiModel
import com.storium.ui.screens.shop.model.PriceUiModel
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.marginPrimary3X

@Composable
fun ProductDetailsContent(
    modifier: Modifier = Modifier,
    product: ProductDetailsUiModel,
) {
    val discountPercent = (product.priceInfo as? PriceUiModel.Discounted)?.discountPercent

    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.spacedBy(marginPrimary3X),
    ) {
        ImageCarousel(
            images = product.images,
            discountPercent = discountPercent,
        )

        ProductDetailsBody(product = product)

        RatingReviewsSection(
            rating = product.rating,
            reviews = product.reviews,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailsContentPreview() {
    StoriumTheme {
        ProductDetailsContent(product = ProductDetailsPreviewUiModels.productWithDiscount)
    }
}
