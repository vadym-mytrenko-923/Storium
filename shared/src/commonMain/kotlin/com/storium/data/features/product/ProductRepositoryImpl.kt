package com.storium.data.features.product

import com.storium.data.features.product.local.source.ProductsLocalDataSource
import com.storium.data.features.product.mapper.toDomainModel
import com.storium.data.features.product.mapper.toDomainModels
import com.storium.data.features.product.model.ProductsFilterParams
import com.storium.data.features.product.remote.source.ProductRemoteDataSource
import com.storium.data.local.storage.app.AppStorage
import com.storium.domain.features.product.ProductRepository
import com.storium.domain.features.product.model.Product
import com.storium.domain.features.product.model.ProductsDataState
import com.storium.domain.system.logger.AppLogger
import com.storium.util.time.currentTimeMillis
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart

private const val SYNC_THRESHOLD_MILLIS = 24 * 3_600_000L // 24 hours

@OptIn(ExperimentalCoroutinesApi::class)
class ProductRepositoryImpl(
    private val remoteDataSource: ProductRemoteDataSource,
    private val localDataSource: ProductsLocalDataSource,
    private val appStorage: AppStorage,
    private val appLogger: AppLogger,
) : ProductRepository {
    private val selectedCategoryIds = MutableStateFlow<List<String>>(emptyList())
    private val searchQuery = MutableStateFlow("")
    private val isSyncInProgress = MutableStateFlow(false)

    override val productsFlow: Flow<ProductsDataState> = combine(
        selectedCategoryIds,
        searchQuery,
        localDataSource.getAllCategoriesFlow(),
        isSyncInProgress,
    ) { selectedIds, query, categories, isLoading ->
        ProductsFilterParams(selectedIds, query, categories, isLoading)
    }.flatMapLatest { params ->
        localDataSource.getProductsFlow(params.selectedCategoryIds, params.query).map { products ->
            ProductsDataState(products = products, categories = params.categories, isLoading = params.isLoading)
        }
    }.onStart {
        syncProductsIfNeeded()
    }

    override val selectedCategoryIdsFlow: Flow<List<String>> = selectedCategoryIds.asStateFlow()

    override val searchQueryFlow: Flow<String> = searchQuery.asStateFlow()

    override fun toggleCategorySelection(categoryId: String) {
        val current = selectedCategoryIds.value
        selectedCategoryIds.value = if (categoryId in current) current - categoryId else current + categoryId
    }

    override fun setSearchQuery(query: String) {
        searchQuery.value = query
    }

    override suspend fun getProductById(id: Int): Product {
        val savedProduct = localDataSource.getProductById(id)
        return savedProduct ?: remoteDataSource.getProductById(id).toDomainModel()
    }

    override suspend fun fetchProducts() {
        isSyncInProgress.value = true
        try {
            val products = remoteDataSource.getProducts().toDomainModels()
            val categories = remoteDataSource.getCategories().toDomainModels()

            localDataSource.saveAll(products, categories)
            appStorage.setProductsSynced()

            selectAllCategoriesIfNeeded(categories.map { it.id })
        } catch (e: Exception) {
            appLogger.logException(e)
        } finally {
            isSyncInProgress.value = false
        }
    }

    private suspend fun syncProductsIfNeeded() {
        val productCount = localDataSource.getProductsCount()
        val lastSyncMillis = appStorage.getLastProductsSyncTimestamp()
        val isSyncExpired = lastSyncMillis == null || currentTimeMillis() - lastSyncMillis > SYNC_THRESHOLD_MILLIS

        if (productCount == 0 || isSyncExpired) {
            fetchProducts()
        } else {
            val categoryIds = localDataSource.getAllCategories().map { it.id }
            selectAllCategoriesIfNeeded(categoryIds)
        }
    }

    private fun selectAllCategoriesIfNeeded(categoryIds: List<String>) {
        if (selectedCategoryIds.value.isEmpty() && categoryIds.isNotEmpty()) {
            selectedCategoryIds.value = categoryIds
        }
    }
}
