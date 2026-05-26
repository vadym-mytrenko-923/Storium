package com.storium.ui.screens.shop.mapper

import com.storium.ui.core.error.model.AppStringResource
import com.storium.ui.screens.shop.model.PriceUiModel
import com.storium.util.reduceByPercent
import com.storium.util.roundTo
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopPriceFormat
import kotlin.math.roundToInt

private const val PRICE_DECIMAL_PLACES = 2

fun toPriceUiModel(price: Double, discountPercentage: Double): PriceUiModel {
    return if (discountPercentage > 0) {
        PriceUiModel.Discounted(
            price = price.reduceByPercent(discountPercentage).toPriceResource(),
            oldPrice = price.toPriceResource(),
            discountPercent = discountPercentage.roundToInt(),
        )
    } else {
        PriceUiModel.Regular(price = price.toPriceResource())
    }
}

private fun Double.toPriceResource(): AppStringResource = AppStringResource(
    res = Res.string.shopPriceFormat,
    args = listOf(this.roundTo(PRICE_DECIMAL_PLACES).toString()),
)
