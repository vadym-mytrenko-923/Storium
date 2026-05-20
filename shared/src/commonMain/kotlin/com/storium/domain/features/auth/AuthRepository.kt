package com.storium.domain.features.auth

import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    val isUserLoggedInFlow: Flow<Boolean>
    suspend fun setLoggedIn(value: Boolean)
    suspend fun logout()
}
