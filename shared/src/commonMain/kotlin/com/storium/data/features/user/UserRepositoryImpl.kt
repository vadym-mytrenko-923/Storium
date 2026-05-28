package com.storium.data.features.user

import com.storium.data.features.user.local.UserLocalDataSource
import com.storium.data.features.user.mapper.toDomainModel
import com.storium.domain.features.auth.model.User
import com.storium.domain.features.user.UserRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserRepositoryImpl(
    private val userLocalDataSource: UserLocalDataSource,
) : UserRepository {
    override val userDataFlow: Flow<User?> = userLocalDataSource.userDataFlow.map { it?.toDomainModel() }

    override suspend fun updateUserData(firstName: String, lastName: String, email: String) {
        val userData = userLocalDataSource.getUserData() ?: return
        userLocalDataSource.setUserData(
            userData = userData.copy(firstName = firstName, lastName = lastName, email = email),
        )
    }
}
