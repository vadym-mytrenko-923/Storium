package com.storium.data.features.product.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CategoryDto(
    @SerialName("slug") val id: String,
    val name: String,
)
