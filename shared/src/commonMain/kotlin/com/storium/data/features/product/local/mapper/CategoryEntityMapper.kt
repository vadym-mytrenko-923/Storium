package com.storium.data.features.product.local.mapper

import com.storium.data.features.product.local.entity.CategoryEntity
import com.storium.domain.features.product.model.Category

fun List<CategoryEntity>.toDomainModels(): List<Category> = map { it.toDomainModel() }

fun List<Category>.toEntities(): List<CategoryEntity> = map { it.toEntity() }

fun CategoryEntity.toDomainModel(): Category = Category(id = id, name = name)

fun Category.toEntity(): CategoryEntity = CategoryEntity(id = id, name = name)
