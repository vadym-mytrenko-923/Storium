package com.storium.data.features.product

import com.storium.data.features.product.mapper.toDomainModel
import com.storium.data.features.product.remote.source.ProductRemoteDataSource
import com.storium.domain.features.product.ProductRepository
import com.storium.domain.features.product.model.Category
import com.storium.domain.features.product.model.Product
import com.storium.domain.features.product.model.ProductsDataState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ProductRepositoryImpl(
    private val remoteDataSource: ProductRemoteDataSource,
) : ProductRepository {
    private val cachedProducts = MutableStateFlow<List<Product>>(emptyList())
    private val cachedCategories = MutableStateFlow<List<Category>>(emptyList())
    private val isSyncInProgress = MutableStateFlow(false)

    override fun getProductsFlow(): Flow<ProductsDataState> = channelFlow {
        launch { fetchInitialDataIfNeeded() }

        combine(cachedProducts, cachedCategories, isSyncInProgress) { products, categories, isLoading ->
            ProductsDataState(
                products = products,
                categories = categories,
                isLoading = isLoading,
            )
        }.collect { send(it) }

        awaitClose()
    }

    override suspend fun fetchProducts() {
        isSyncInProgress.value = true
        try {
            val products = remoteDataSource.getProducts().map { it.toDomainModel() }
            cachedProducts.value = products
        } finally {
            isSyncInProgress.value = false
        }
    }

    override suspend fun fetchProductsByCategory(id: String) {
        isSyncInProgress.value = true
        try {
            val products = remoteDataSource.getProductsByCategory(id).map { it.toDomainModel() }
            cachedProducts.value = products
        } finally {
            isSyncInProgress.value = false
        }
    }

    // TODO review if refactor is needed
    private suspend fun fetchInitialDataIfNeeded() {
        if (cachedProducts.value.isNotEmpty()) return

        isSyncInProgress.value = true
        try {
            val categories = remoteDataSource.getCategories().map { it.toDomainModel() }
            cachedCategories.value = categories

            val products = remoteDataSource.getProducts().map { it.toDomainModel() }
            cachedProducts.value = products
        } finally {
            isSyncInProgress.value = false
        }
    }
}
