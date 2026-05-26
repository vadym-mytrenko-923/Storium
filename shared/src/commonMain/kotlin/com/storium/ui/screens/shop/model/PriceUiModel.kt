package com.storium.ui.screens.shop.model

import com.storium.ui.core.error.model.AppStringResource

sealed class PriceUiModel(open val price: AppStringResource) {
    data class Regular(override val price: AppStringResource) : PriceUiModel(price)

    data class Discounted(
        override val price: AppStringResource,
        val oldPrice: AppStringResource,
        val discountPercent: Int,
    ) : PriceUiModel(price)
}
