package com.storium.domain.features.product.usecase

import com.storium.domain.base.usecase.BaseNoParamsFlowUseCase
import com.storium.domain.features.product.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetProductsSearchQueryFlowUseCase(
    private val repository: ProductRepository,
) : BaseNoParamsFlowUseCase<String>() {
    override fun execute(): Flow<String> = repository.searchQueryFlow
}
