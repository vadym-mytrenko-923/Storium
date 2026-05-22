package com.storium.ui.screens.shop.composable.common

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimaryHalf
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopPriceFormat

@Composable
fun ProductPriceRow(
    modifier: Modifier = Modifier,
    price: Int,
    oldPrice: Int? = null,
) {
    Row(
        modifier = modifier,
        horizontalArrangement = Arrangement.spacedBy(marginPrimaryHalf),
    ) {
        if (oldPrice != null) {
            Text(
                text = stringResource(Res.string.shopPriceFormat, oldPrice),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.appColors.textSecondary,
                textDecoration = TextDecoration.LineThrough,
            )

            Text(
                text = stringResource(Res.string.shopPriceFormat, price),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.appColors.primary,
            )
        } else {
            Text(
                text = stringResource(Res.string.shopPriceFormat, price),
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.appColors.textPrimary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductPriceRowPreview() {
    StoriumTheme {
        ProductPriceRow(price = 51)
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductPriceRowDiscountPreview() {
    StoriumTheme {
        ProductPriceRow(price = 14, oldPrice = 21)
    }
}
