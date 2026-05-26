package com.storium.data.features.product.mapper

import com.storium.data.features.product.remote.model.CategoryDto
import com.storium.domain.features.product.model.Category

fun List<CategoryDto>.toDomainModels(): List<Category> = map { it.toDomainModel() }

fun CategoryDto.toDomainModel(): Category = Category(
    id = id,
    name = name,
)
