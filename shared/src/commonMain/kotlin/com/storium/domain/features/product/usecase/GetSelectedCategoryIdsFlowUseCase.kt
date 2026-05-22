package com.storium.domain.features.product.usecase

import com.storium.domain.base.usecase.BaseNoParamsFlowUseCase
import com.storium.domain.features.product.ProductRepository
import kotlinx.coroutines.flow.Flow

class GetSelectedCategoryIdsFlowUseCase(
    private val repository: ProductRepository,
) : BaseNoParamsFlowUseCase<Set<String>>() {
    override fun execute(): Flow<Set<String>> = repository.selectedCategoryIdsFlow
}
