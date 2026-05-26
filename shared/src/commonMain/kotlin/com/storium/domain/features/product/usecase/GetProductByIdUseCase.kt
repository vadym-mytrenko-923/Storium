package com.storium.domain.features.product.usecase

import com.storium.domain.base.result.useResultWrapper
import com.storium.domain.base.usecase.BaseUseCase
import com.storium.domain.features.product.ProductRepository
import com.storium.domain.features.product.model.Product

class GetProductByIdUseCase(
    private val repository: ProductRepository,
) : BaseUseCase<Int, Result<Product>>() {
    override suspend fun execute(parameters: Int): Result<Product> = useResultWrapper {
        repository.getProductById(parameters)
    }
}
