package com.storium.domain.features.product.usecase

import com.storium.domain.features.product.ProductRepository

class ToggleCategorySelectionUseCase(private val repository: ProductRepository) {
    operator fun invoke(categoryId: String) = repository.toggleCategorySelection(categoryId)
}
