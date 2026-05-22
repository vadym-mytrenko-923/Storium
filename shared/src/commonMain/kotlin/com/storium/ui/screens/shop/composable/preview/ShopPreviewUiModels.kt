package com.storium.ui.screens.shop.composable.preview

import com.storium.ui.screens.shop.model.CategoryUiModel
import com.storium.ui.screens.shop.model.ProductUiModel

private const val PREVIEW_PRODUCTS_COUNT = 4

object ShopPreviewUiModels {
    val product = ProductUiModel(
        id = 1,
        title = "Pullover",
        brand = "Mango",
        thumbnail = "",
        price = 51,
        oldPrice = null,
        discountPercent = null,
        rating = 4.0,
        reviewCount = 3,
    )

    val productWithDiscount = ProductUiModel(
        id = 2,
        title = "Blouse",
        brand = "Dorothy Perkins",
        thumbnail = "",
        price = 14,
        oldPrice = 21,
        discountPercent = 20,
        rating = 5.0,
        reviewCount = 10,
    )

    val products = List(PREVIEW_PRODUCTS_COUNT) { index ->
        if (index % 2 == 0) product.copy(id = index) else productWithDiscount.copy(id = index)
    }

    val categories = listOf(
        CategoryUiModel("beauty", "Beauty", true),
        CategoryUiModel("fragrances", "Fragrances", true),
        CategoryUiModel("furniture", "Furniture", false),
        CategoryUiModel("laptops", "Laptops", true),
    )
}
