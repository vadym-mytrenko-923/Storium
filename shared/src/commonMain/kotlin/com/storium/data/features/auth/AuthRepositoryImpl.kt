package com.storium.data.features.auth

import com.storium.data.features.auth.local.AuthLocalDataSource
import com.storium.domain.features.auth.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(private val localDataSource: AuthLocalDataSource) : AuthRepository {
    override val isUserLoggedInFlow: Flow<Boolean> = localDataSource.isLoggedInFlow

    override suspend fun setLoggedIn(value: Boolean) = localDataSource.setIsLoggedIn(value)

    override suspend fun logout() = localDataSource.clear()
}
