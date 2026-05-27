package com.storium.data.features.product.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = ReviewEntity.TABLE_NAME,
    foreignKeys = [
        ForeignKey(
            entity = ProductEntity::class,
            parentColumns = ["id"],
            childColumns = ["productId"],
            onDelete = ForeignKey.CASCADE,
        ),
    ],
    indices = [Index("productId")],
)
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true) val localId: Long = 0,
    val productId: Int,
    val rating: Int,
    val comment: String,
    val reviewerName: String,
    val date: String,
) {
    companion object {
        const val TABLE_NAME = "reviews"
    }
}
