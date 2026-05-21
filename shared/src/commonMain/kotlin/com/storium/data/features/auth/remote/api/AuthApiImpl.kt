package com.storium.data.features.auth.remote.api

import com.storium.data.features.auth.remote.model.LoginRequestDto
import com.storium.data.features.auth.remote.model.LoginResponseDto
import com.storium.data.remote.postRequest
import io.ktor.client.HttpClient

class AuthApiImpl(private val httpClient: HttpClient) : AuthApi {
    override suspend fun login(
        request: LoginRequestDto
    ): LoginResponseDto = httpClient.postRequest("/auth/login", request)
}
