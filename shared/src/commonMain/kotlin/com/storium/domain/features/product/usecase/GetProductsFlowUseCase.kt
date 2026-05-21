package com.storium.domain.features.product.usecase

import com.storium.domain.base.usecase.BaseNoParamsFlowUseCase
import com.storium.domain.features.product.ProductRepository
import com.storium.domain.features.product.model.ProductsDataState
import kotlinx.coroutines.flow.Flow

class GetProductsFlowUseCase(private val repository: ProductRepository) : BaseNoParamsFlowUseCase<ProductsDataState>() {
    override fun execute(): Flow<ProductsDataState> = repository.getProductsFlow()
}
