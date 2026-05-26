package com.storium.data.features.product

import com.storium.data.features.product.mapper.toDomainModel
import com.storium.data.features.product.mapper.toDomainModels
import com.storium.data.features.product.remote.source.ProductRemoteDataSource
import com.storium.domain.features.product.ProductRepository
import com.storium.domain.features.product.model.Category
import com.storium.domain.features.product.model.Product
import com.storium.domain.features.product.model.ProductsDataState
import com.storium.domain.system.logger.AppLogger
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.onStart

class ProductRepositoryImpl(
    private val remoteDataSource: ProductRemoteDataSource,
    private val appLogger: AppLogger,
) : ProductRepository {
    private val cachedProducts = MutableStateFlow<List<Product>>(emptyList())
    private val cachedCategories = MutableStateFlow<List<Category>>(emptyList())
    private val selectedCategoryIds = MutableStateFlow<Set<String>>(emptySet())
    private val searchQuery = MutableStateFlow("")
    private val isSyncInProgress = MutableStateFlow(false)

    override val productsFlow: Flow<ProductsDataState> = combine(
        cachedProducts,
        cachedCategories,
        selectedCategoryIds,
        isSyncInProgress,
        searchQuery,
    ) { products, categories, selectedIds, isLoading, query ->
        val categoryFiltered = if (selectedIds.isEmpty()) {
            emptyList()
        } else {
            products.filter { it.categoryId in selectedIds }
        }

        val searchFiltered = if (query.isBlank()) {
            categoryFiltered
        } else {
            categoryFiltered.filter {
                it.title.contains(query, ignoreCase = true) || it.brand.contains(query, ignoreCase = true)
            }
        }

        ProductsDataState(
            products = searchFiltered,
            categories = categories,
            isLoading = isLoading,
        )
    }.onStart {
        fetchInitialDataIfNeeded()
    }

    override val selectedCategoryIdsFlow: Flow<Set<String>> = selectedCategoryIds.asStateFlow()

    override val searchQueryFlow: Flow<String> = searchQuery.asStateFlow()

    override fun toggleCategorySelection(categoryId: String) {
        val current = selectedCategoryIds.value
        val categoryIds = cachedCategories.value.map { it.id }.toSet()
        val updatedCategories = if (categoryId in current) current - categoryId else current + categoryId

        selectedCategoryIds.value = if (updatedCategories.size == categoryIds.size) categoryIds else updatedCategories
    }

    override fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    override suspend fun getProductById(id: Int): Product {
        return remoteDataSource.getProductById(id).toDomainModel()
    }

    override suspend fun fetchProducts() {
        isSyncInProgress.value = true
        try {
            cachedProducts.value = remoteDataSource.getProducts().toDomainModels()
        } catch (e: Exception) {
            appLogger.logException(e)
        } finally {
            isSyncInProgress.value = false
        }
    }

    override suspend fun fetchProductsByCategory(id: String) {
        isSyncInProgress.value = true
        try {
            cachedProducts.value = remoteDataSource.getProductsByCategory(id).toDomainModels()
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
            val categories = remoteDataSource.getCategories().toDomainModels()
            val products = remoteDataSource.getProducts().toDomainModels()

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
