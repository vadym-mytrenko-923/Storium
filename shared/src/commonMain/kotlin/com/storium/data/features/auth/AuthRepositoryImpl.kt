package com.storium.data.features.auth

import com.storium.data.features.user.local.UserLocalDataSource
import com.storium.domain.features.auth.AuthRepository
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(private val userLocalDataSource: UserLocalDataSource) : AuthRepository {
    override val isUserLoggedInFlow: Flow<Boolean> = userLocalDataSource.isLoggedInFlow

    override suspend fun setLoggedIn(value: Boolean) = userLocalDataSource.setIsLoggedIn(value)

    override suspend fun logout() = userLocalDataSource.clear()
}
