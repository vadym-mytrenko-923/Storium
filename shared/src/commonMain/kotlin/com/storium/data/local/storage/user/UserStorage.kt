package com.storium.data.local.storage.user

import kotlinx.coroutines.flow.Flow

interface UserStorage {
    val isLoggedInFlow: Flow<Boolean>
    suspend fun setIsLoggedIn(value: Boolean)
    suspend fun clear()
}
