package com.storium.data.features.auth.local

import kotlinx.coroutines.flow.MutableStateFlow

class AuthLocalDataSourceImpl : AuthLocalDataSource {
    override val isLoggedInFlow = MutableStateFlow(false)

    override suspend fun setIsLoggedIn(value: Boolean) {
        isLoggedInFlow.value = value
    }

    override suspend fun clear() {
        isLoggedInFlow.value = false
    }
}
