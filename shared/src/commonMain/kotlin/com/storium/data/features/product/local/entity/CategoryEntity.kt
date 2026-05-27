package com.storium.data.features.product.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = CategoryEntity.TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey val id: String,
    val name: String,
) {
    companion object {
        const val TABLE_NAME = "categories"
    }
}
