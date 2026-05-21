package com.storium.data.features.user.local

import com.storium.data.local.storage.user.UserStorage
import kotlinx.coroutines.flow.Flow

class UserLocalDataSourceImpl(private val userStorage: UserStorage) : UserLocalDataSource {
    override val isLoggedInFlow: Flow<Boolean> = userStorage.isLoggedInFlow

    override suspend fun setIsLoggedIn(value: Boolean) = userStorage.setIsLoggedIn(value)

    override suspend fun clear() = userStorage.clear()
}
