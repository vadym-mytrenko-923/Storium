package com.storium.data.features.user.local.model

import kotlinx.serialization.Serializable

@Serializable
data class UserData(
    val id: Int = 0,
    val username: String = "",
    val email: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val image: String = "",
)
