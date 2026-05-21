package com.storium.data.features.auth.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginResponseDto(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String,
    val accessToken: String,
    val refreshToken: String,
)
