package com.storium.domain.features.product.usecase

import com.storium.domain.base.usecase.BaseUseCase
import com.storium.domain.features.product.ProductRepository

class SetProductsSearchQueryUseCase(
    private val repository: ProductRepository,
) : BaseUseCase<String, Unit>() {
    override suspend fun execute(parameters: String) = repository.setSearchQuery(parameters)
}
