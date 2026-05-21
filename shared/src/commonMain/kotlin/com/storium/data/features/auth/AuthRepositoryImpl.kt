package com.storium.data.features.auth

import com.storium.data.features.auth.mapper.toDomainModel
import com.storium.data.features.auth.remote.source.AuthRemoteDataSource
import com.storium.data.features.user.local.UserLocalDataSource
import com.storium.domain.features.auth.AuthRepository
import com.storium.domain.features.auth.model.LoginParams
import com.storium.domain.features.auth.model.User
import kotlinx.coroutines.flow.Flow

class AuthRepositoryImpl(
    private val authRemoteDataSource: AuthRemoteDataSource,
    private val userLocalDataSource: UserLocalDataSource,
) : AuthRepository {
    override val isUserLoggedInFlow: Flow<Boolean> = userLocalDataSource.isLoggedInFlow

    override suspend fun login(params: LoginParams): User {
        val response = authRemoteDataSource.login(params.username, params.password)
        return response.toDomainModel()
    }

    override suspend fun setLoggedIn(value: Boolean) = userLocalDataSource.setIsLoggedIn(value)

    override suspend fun logout() = userLocalDataSource.clear()
}
