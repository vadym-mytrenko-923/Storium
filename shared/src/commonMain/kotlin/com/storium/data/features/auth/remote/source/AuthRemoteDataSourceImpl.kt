package com.storium.data.features.auth.remote.source

import com.storium.data.features.auth.remote.api.AuthApi
import com.storium.data.features.auth.remote.model.LoginRequestDto
import com.storium.data.features.auth.remote.model.LoginResponseDto

class AuthRemoteDataSourceImpl(private val authApi: AuthApi) : AuthRemoteDataSource {
    override suspend fun login(username: String, password: String): LoginResponseDto = authApi.login(
        request = LoginRequestDto(username = username, password = password)
    )
}
