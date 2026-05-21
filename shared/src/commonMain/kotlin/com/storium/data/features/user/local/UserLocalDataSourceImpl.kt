package com.storium.data.features.user.local

import com.storium.data.features.user.local.model.UserData
import com.storium.data.local.storage.user.UserStorage
import kotlinx.coroutines.flow.Flow

class UserLocalDataSourceImpl(private val userStorage: UserStorage) : UserLocalDataSource {
    override val isLoggedInFlow: Flow<Boolean> = userStorage.isLoggedInFlow
    override val userDataFlow: Flow<UserData?> = userStorage.userDataFlow
    override suspend fun setIsLoggedIn(value: Boolean) = userStorage.setIsLoggedIn(value)
    override suspend fun setUserData(userData: UserData) = userStorage.setUserData(userData)
    override suspend fun getUserData(): UserData? = userStorage.getUserData()
    override suspend fun clear() = userStorage.clear()
}
