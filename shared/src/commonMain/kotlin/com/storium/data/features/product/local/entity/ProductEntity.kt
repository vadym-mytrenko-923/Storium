package com.storium.data.features.product.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = ProductEntity.TABLE_NAME)
data class ProductEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val description: String,
    val price: Double,
    val discountPercentage: Double,
    val brand: String,
    val thumbnail: String,
    val images: String,
    val categoryId: String,
) {
    companion object {
        const val TABLE_NAME = "products"
    }
}
