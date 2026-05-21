package com.storium.data.features.user.local

import kotlinx.coroutines.flow.Flow

interface UserLocalDataSource {
    val isLoggedInFlow: Flow<Boolean>
    suspend fun setIsLoggedIn(value: Boolean)
    suspend fun clear()
}
