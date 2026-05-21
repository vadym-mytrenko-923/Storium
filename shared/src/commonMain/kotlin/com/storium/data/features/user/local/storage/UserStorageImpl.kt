package com.storium.data.features.user.local.storage

import com.storium.data.features.user.local.model.UserData
import com.storium.data.local.storage.SecureStorage
import com.storium.data.local.storage.getJsonFlowValue
import com.storium.data.local.storage.getJsonValue
import com.storium.data.local.storage.putJsonValue
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

private const val KEY_IS_LOGGED_IN = "is_logged_in"
private const val KEY_USER_DATA = "user_data"

class UserStorageImpl(
    private val secureStorage: SecureStorage,
    private val json: Json,
) : UserStorage {
    override val isLoggedInFlow: Flow<Boolean> = secureStorage.getFlowValue(KEY_IS_LOGGED_IN).map {
        it?.toBooleanStrictOrNull() ?: false
    }

    override val userDataFlow: Flow<UserData?> = secureStorage.getJsonFlowValue(KEY_USER_DATA, json)

    override suspend fun setIsLoggedIn(value: Boolean) = secureStorage.putValue(KEY_IS_LOGGED_IN, value.toString())

    override suspend fun setUserData(userData: UserData) = secureStorage.putJsonValue(KEY_USER_DATA, json, userData)

    override suspend fun getUserData(): UserData? = secureStorage.getJsonValue(KEY_USER_DATA, json)

    override suspend fun clear() = secureStorage.clear()
}
