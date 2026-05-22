package com.storium.data.features.product

import com.storium.data.features.product.mapper.toDomainModel
import com.storium.data.features.product.remote.source.ProductRemoteDataSource
import com.storium.domain.features.product.ProductRepository
import com.storium.domain.features.product.model.Category
import com.storium.domain.features.product.model.Product
import com.storium.domain.features.product.model.ProductsDataState
import com.storium.domain.system.logger.AppLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.channelFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class ProductRepositoryImpl(
    private val remoteDataSource: ProductRemoteDataSource,
    private val appLogger: AppLogger,
) : ProductRepository {
    private val cachedProducts = MutableStateFlow<List<Product>>(emptyList())
    private val cachedCategories = MutableStateFlow<List<Category>>(emptyList())
    private val selectedCategoryIds = MutableStateFlow<Set<String>>(emptySet())
    private val isSyncInProgress = MutableStateFlow(false)

    override val productsFlow: Flow<ProductsDataState> = channelFlow {
        launch { fetchInitialDataIfNeeded() }

        combine(
            cachedProducts,
            cachedCategories,
            selectedCategoryIds,
            isSyncInProgress,
        ) { products, categories, selectedIds, isLoading ->
            val filteredProducts = if (selectedIds.isEmpty()) {
                products
            } else {
                products.filter { it.category in selectedIds }
            }

            ProductsDataState(
                products = filteredProducts,
                categories = categories,
                isLoading = isLoading,
            )
        }.collect {
            send(it)
        }
    }

    override val selectedCategoryIdsFlow: Flow<Set<String>> = selectedCategoryIds.asStateFlow()

    override fun toggleCategorySelection(categoryId: String) {
        val current = selectedCategoryIds.value
        val categoryIds = cachedCategories.value.map { it.id }.toSet()
        val updatedCategories = if (categoryId in current) current - categoryId else current + categoryId

        selectedCategoryIds.value = if (updatedCategories.size == categoryIds.size) categoryIds else updatedCategories
    }

    override suspend fun fetchProducts() {
        isSyncInProgress.value = true
        try {
            cachedProducts.value = remoteDataSource.getProducts().map { it.toDomainModel() }
        } catch (e: Exception) {
            appLogger.logException(e)
        } finally {
            isSyncInProgress.value = false
        }
    }

    override suspend fun fetchProductsByCategory(id: String) {
        isSyncInProgress.value = true
        try {
            cachedProducts.value = remoteDataSource.getProductsByCategory(id).map { it.toDomainModel() }
        } catch (e: Exception) {
            appLogger.logException(e)
        } finally {
            isSyncInProgress.value = false
        }
    }

    private suspend fun fetchInitialDataIfNeeded() {
        if (cachedProducts.value.isNotEmpty()) return

        isSyncInProgress.value = true
        try {
            val categories = remoteDataSource.getCategories().map { it.toDomainModel() }
            val products = remoteDataSource.getProducts().map { it.toDomainModel() }

            cachedProducts.value = products
            cachedCategories.value = categories
            selectedCategoryIds.value = categories.map { it.id }.toSet()
        } catch (e: Exception) {
            appLogger.logException(e)
        } finally {
            isSyncInProgress.value = false
        }
    }
}
