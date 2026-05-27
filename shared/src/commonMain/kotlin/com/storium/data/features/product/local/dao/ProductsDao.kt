package com.storium.data.features.product.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.storium.data.features.product.local.entity.CategoryEntity
import com.storium.data.features.product.local.entity.ProductEntity
import com.storium.data.features.product.local.entity.ProductWithReviews
import com.storium.data.features.product.local.entity.ReviewEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface ProductsDao {
    // Products
    @Transaction
    @Query(
        "SELECT * FROM products WHERE categoryId IN (:categoryIds) " +
            "AND (:query = '' OR title LIKE '%' || :query || '%' OR brand LIKE '%' || :query || '%')",
    )
    fun getProductsFlow(categoryIds: List<String>, query: String): Flow<List<ProductWithReviews>>

    @Transaction
    @Query("SELECT * FROM products WHERE id = :id")
    suspend fun getProductById(id: Int): ProductWithReviews?

    @Query("SELECT COUNT(*) FROM products")
    suspend fun getProductsCount(): Int

    // Categories
    @Query("SELECT * FROM categories")
    fun getAllCategoriesFlow(): Flow<List<CategoryEntity>>

    @Query("SELECT * FROM categories")
    suspend fun getAllCategories(): List<CategoryEntity>

    // Inserts
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertProducts(products: List<ProductEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCategories(categories: List<CategoryEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertReviews(reviews: List<ReviewEntity>)

    @Transaction
    suspend fun insertAll(
        products: List<ProductEntity>,
        categories: List<CategoryEntity>,
        reviews: List<ReviewEntity>,
    ) {
        deleteAllReviews()
        deleteAllProducts()
        deleteAllCategories()
        insertProducts(products)
        insertCategories(categories)
        insertReviews(reviews)
    }

    // Deletes
    @Query("DELETE FROM reviews")
    suspend fun deleteAllReviews()

    @Query("DELETE FROM products")
    suspend fun deleteAllProducts()

    @Query("DELETE FROM categories")
    suspend fun deleteAllCategories()
}
