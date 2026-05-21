package com.storium.data.features.auth.remote.api

import com.storium.data.features.auth.remote.model.LoginRequestDto
import com.storium.data.features.auth.remote.model.LoginResponseDto

interface AuthApi {
    suspend fun login(request: LoginRequestDto): LoginResponseDto
}
