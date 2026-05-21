package com.storium.data.features.product.mapper

import com.storium.data.features.product.remote.model.CategoryDto
import com.storium.domain.features.product.model.Category

fun CategoryDto.toDomainModel(): Category = Category(
    id = id,
    name = name,
)
