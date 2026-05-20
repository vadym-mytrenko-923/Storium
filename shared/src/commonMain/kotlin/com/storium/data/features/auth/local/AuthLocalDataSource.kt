package com.storium.data.features.auth.local

import kotlinx.coroutines.flow.Flow

interface AuthLocalDataSource {
    val isLoggedInFlow: Flow<Boolean>
    suspend fun setIsLoggedIn(value: Boolean)
    suspend fun clear()
}
