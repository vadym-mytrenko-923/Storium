package com.storium.ui.screens.product.details.composable

import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.product.details.composable.preview.ProductDetailsPreviewUiModels
import com.storium.ui.screens.product.details.model.ProductDetailsUiModel
import com.storium.ui.screens.shop.composable.common.price.PriceRowStyle
import com.storium.ui.screens.shop.composable.common.price.ProductPriceRow
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary2X

@Composable
fun ProductDetailsBody(
    modifier: Modifier = Modifier,
    product: ProductDetailsUiModel,
) {
    val hasBrand = product.brand.isNotEmpty()

    Column(
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = marginPrimary2X),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                modifier = Modifier.weight(1f, fill = false),
                text = if (hasBrand) product.brand else product.title,
                style = MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium),
                color = MaterialTheme.appColors.textPrimary,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )

            ProductPriceRow(
                priceInfo = product.priceInfo,
                style = PriceRowStyle.Large,
            )
        }

        if (hasBrand) {
            Text(
                text = product.title,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.appColors.textSecondary,
            )
        }

        if (product.description.isNotEmpty()) {
            Spacer(modifier = Modifier.height(marginPrimary2X))

            Text(
                text = product.description,
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.appColors.textPrimary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailsBodyPreview() {
    StoriumTheme {
        ProductDetailsBody(product = ProductDetailsPreviewUiModels.productWithDiscount)
    }
}
