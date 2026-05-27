package com.storium.domain.features.product.usecase

import com.storium.domain.base.usecase.BaseNoParamsUseCase
import com.storium.domain.features.product.ProductRepository

class RefreshProductsUseCase(private val repository: ProductRepository) : BaseNoParamsUseCase<Unit>() {
    override suspend fun execute() = repository.fetchProducts()
}
