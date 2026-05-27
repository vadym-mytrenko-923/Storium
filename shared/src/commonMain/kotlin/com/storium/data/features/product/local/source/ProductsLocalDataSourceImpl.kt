package com.storium.data.features.product.local.source

import com.storium.data.features.product.local.dao.ProductsDao
import com.storium.data.features.product.local.mapper.toDomainModel
import com.storium.data.features.product.local.mapper.toDomainModels
import com.storium.data.features.product.local.mapper.toEntities
import com.storium.data.features.product.local.mapper.toEntity
import com.storium.domain.features.product.model.Category
import com.storium.domain.features.product.model.Product
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class ProductsLocalDataSourceImpl(
    private val productsDao: ProductsDao,
) : ProductsLocalDataSource {
    override fun getProductsFlow(categoryIds: List<String>, query: String): Flow<List<Product>> {
        return productsDao.getProductsFlow(categoryIds, query).map { it.toDomainModels() }
    }

    override fun getAllCategoriesFlow(): Flow<List<Category>> {
        return productsDao.getAllCategoriesFlow().map { it.toDomainModels() }
    }

    override suspend fun getAllCategories(): List<Category> = productsDao.getAllCategories().toDomainModels()

    override suspend fun getProductById(id: Int): Product? {
        return productsDao.getProductById(id)?.toDomainModel()
    }

    override suspend fun getProductsCount(): Int = productsDao.getProductsCount()

    override suspend fun saveAll(products: List<Product>, categories: List<Category>) {
        val productEntities = products.toEntities()
        val categoryEntities = categories.toEntities()
        val reviewEntities = products.flatMap { product ->
            product.reviews.map { review -> review.toEntity(productId = product.id) }
        }

        productsDao.insertAll(productEntities, categoryEntities, reviewEntities)
    }
}
