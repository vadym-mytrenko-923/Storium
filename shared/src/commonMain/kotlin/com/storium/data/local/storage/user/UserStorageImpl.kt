package com.storium.data.local.storage.user

import com.storium.data.local.SecureStorage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val KEY_IS_LOGGED_IN = "is_logged_in"

class UserStorageImpl(private val secureStorage: SecureStorage) : UserStorage {
    override val isLoggedInFlow: Flow<Boolean> = secureStorage.getFlowValue(KEY_IS_LOGGED_IN).map {
        it?.toBooleanStrictOrNull() ?: true
    }

    override suspend fun setIsLoggedIn(value: Boolean) = secureStorage.putValue(KEY_IS_LOGGED_IN, value.toString())

    override suspend fun clear() = secureStorage.clear()
}
