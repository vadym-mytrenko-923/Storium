package com.storium.ui.screens.shop.composable.common.price

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.core.error.model.AppStringResource
import com.storium.ui.screens.shop.model.PriceUiModel
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimaryHalf
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopPriceFormat

@Composable
fun ProductPriceRow(
    modifier: Modifier = Modifier,
    priceInfo: PriceUiModel,
    style: PriceRowStyle = PriceRowStyle.Small,
) {
    val textStyle = when (style) {
        PriceRowStyle.Small -> MaterialTheme.typography.labelLarge
        PriceRowStyle.Large -> MaterialTheme.typography.headlineMedium.copy(fontWeight = FontWeight.Medium)
    }

    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(marginPrimaryHalf),
    ) {
        when (priceInfo) {
            is PriceUiModel.Discounted -> {
                PriceText(
                    resource = priceInfo.oldPrice,
                    textStyle = textStyle,
                    color = MaterialTheme.appColors.textSecondary,
                    textDecoration = TextDecoration.LineThrough,
                )

                PriceText(
                    resource = priceInfo.price,
                    textStyle = textStyle,
                    color = MaterialTheme.appColors.primary,
                )
            }

            is PriceUiModel.Regular -> {
                PriceText(
                    resource = priceInfo.price,
                    textStyle = textStyle,
                    color = MaterialTheme.appColors.textPrimary,
                )
            }
        }
    }
}

@Composable
private fun PriceText(
    resource: AppStringResource,
    textStyle: TextStyle,
    color: androidx.compose.ui.graphics.Color,
    textDecoration: TextDecoration? = null,
) {
    Text(
        text = stringResource(resource.res, *resource.args.toTypedArray()),
        style = textStyle,
        color = color,
        textDecoration = textDecoration,
    )
}

@Preview(showBackground = true)
@Composable
private fun ProductPriceRowSmallPreview() {
    StoriumTheme {
        ProductPriceRow(
            priceInfo = PriceUiModel.Regular(
                price = AppStringResource(Res.string.shopPriceFormat, listOf("51.00")),
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductPriceRowSmallDiscountPreview() {
    StoriumTheme {
        ProductPriceRow(
            priceInfo = PriceUiModel.Discounted(
                price = AppStringResource(Res.string.shopPriceFormat, listOf("14.99")),
                oldPrice = AppStringResource(Res.string.shopPriceFormat, listOf("21.00")),
                discountPercent = 20,
            ),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductPriceRowLargeDiscountPreview() {
    StoriumTheme {
        ProductPriceRow(
            priceInfo = PriceUiModel.Discounted(
                price = AppStringResource(Res.string.shopPriceFormat, listOf("449.00")),
                oldPrice = AppStringResource(Res.string.shopPriceFormat, listOf("549.00")),
                discountPercent = 18,
            ),
            style = PriceRowStyle.Large,
        )
    }
}
