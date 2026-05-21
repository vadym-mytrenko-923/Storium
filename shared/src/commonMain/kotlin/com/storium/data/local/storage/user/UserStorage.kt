package com.storium.data.local.storage.user

import com.storium.data.features.user.local.model.UserData
import kotlinx.coroutines.flow.Flow

interface UserStorage {
    val isLoggedInFlow: Flow<Boolean>
    val userDataFlow: Flow<UserData?>
    suspend fun setIsLoggedIn(value: Boolean)
    suspend fun setUserData(userData: UserData)
    suspend fun getUserData(): UserData?
    suspend fun clear()
}
