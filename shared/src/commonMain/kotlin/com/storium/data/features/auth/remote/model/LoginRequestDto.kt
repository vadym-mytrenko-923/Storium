package com.storium.data.features.auth.remote.model

import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestDto(
    val username: String,
    val password: String,
)
