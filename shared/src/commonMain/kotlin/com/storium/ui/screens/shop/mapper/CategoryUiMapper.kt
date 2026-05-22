package com.storium.ui.screens.shop.mapper

import com.storium.domain.features.product.model.Category
import com.storium.ui.screens.shop.model.CategoryUiModel

fun List<Category>.toUiModels(selectedIds: Set<String>): List<CategoryUiModel> = map {
    it.toUiModel(isSelected = it.id in selectedIds)
}

fun Category.toUiModel(isSelected: Boolean): CategoryUiModel = CategoryUiModel(
    id = id,
    name = name,
    isSelected = isSelected,
)
