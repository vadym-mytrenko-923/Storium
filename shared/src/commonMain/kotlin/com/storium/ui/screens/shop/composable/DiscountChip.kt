package com.storium.ui.screens.shop.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.chipShapeDefault
import com.storium.ui.theme.discountChipHeight
import com.storium.ui.theme.discountChipPadding
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopDiscountFormat

@Composable
fun DiscountChip(
    modifier: Modifier = Modifier,
    discountPercent: Int,
) {
    Box(
        modifier = modifier
            .height(discountChipHeight)
            .background(
                color = MaterialTheme.appColors.primary,
                shape = chipShapeDefault,
            )
            .padding(horizontal = discountChipPadding),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = stringResource(Res.string.shopDiscountFormat, discountPercent),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.appColors.btnPrimaryText,
        )
    }
}

@Preview
@Composable
private fun DiscountChipPreview() {
    StoriumTheme {
        DiscountChip(discountPercent = 20)
    }
}
