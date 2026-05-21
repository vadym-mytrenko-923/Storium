package com.storium.data.features.auth.remote.source

import com.storium.data.features.auth.remote.model.LoginResponseDto

interface AuthRemoteDataSource {
    suspend fun login(username: String, password: String): LoginResponseDto
}
