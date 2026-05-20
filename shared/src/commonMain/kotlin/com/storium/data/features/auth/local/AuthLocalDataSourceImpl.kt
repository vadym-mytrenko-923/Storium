package com.storium.data.features.auth.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import com.storium.data.local.clearAll
import com.storium.data.local.getFlowValue
import com.storium.data.local.putValue
import kotlinx.coroutines.flow.Flow

private val KEY_IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")

class AuthLocalDataSourceImpl(
    private val dataStore: DataStore<Preferences>,
) : AuthLocalDataSource {
    override val isLoggedInFlow: Flow<Boolean> = dataStore.getFlowValue(KEY_IS_LOGGED_IN, false)

    override suspend fun setIsLoggedIn(value: Boolean) = dataStore.putValue(KEY_IS_LOGGED_IN, value)

    override suspend fun clear() = dataStore.clearAll()
}
