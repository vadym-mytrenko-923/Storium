package com.storium.data.features.product.local.entity

import androidx.room.Embedded
import androidx.room.Relation

data class ProductWithReviews(
    @Embedded val product: ProductEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "productId",
    )
    val reviews: List<ReviewEntity>,
)
