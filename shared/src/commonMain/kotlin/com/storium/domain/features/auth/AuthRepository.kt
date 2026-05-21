package com.storium.domain.features.auth

import com.storium.domain.features.auth.model.LoginParams
import com.storium.domain.features.auth.model.User
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val isUserLoggedInFlow: Flow<Boolean>
    suspend fun login(params: LoginParams): User
    suspend fun setLoggedIn(value: Boolean)
    suspend fun logout()
}
