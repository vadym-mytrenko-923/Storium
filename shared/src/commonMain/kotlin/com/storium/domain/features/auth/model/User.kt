package com.storium.domain.features.auth.model

data class User(
    val id: Int,
    val username: String,
    val email: String,
    val firstName: String,
    val lastName: String,
    val image: String,
)
