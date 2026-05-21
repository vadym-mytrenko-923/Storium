package com.storium.data.local.storage

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.google.crypto.tink.Aead
import com.storium.data.local.SecureStorage
import kotlinx.coroutines.flow.Flow
import okio.Path.Companion.toPath

fun createSecureStorage(context: Context, name: String, encryption: Aead): SecureStorage {
    val dataStore = PreferenceDataStoreFactory.createWithPath(
        produceFile = { context.filesDir.resolve("$name.preferences_pb").absolutePath.toPath() },
    )

    return DataStoreSecureStorage(dataStore = dataStore, encryption = encryption)
}

private class DataStoreSecureStorage(
    private val dataStore: DataStore<Preferences>,
    private val encryption: Aead,
) : SecureStorage {
    override fun getFlowValue(key: String): Flow<String?> {
        return dataStore.getFlowValue(stringPreferencesKey(key), encryption)
    }

    override suspend fun putValue(key: String, value: String) {
        dataStore.putValue(stringPreferencesKey(key), encryption, value)
    }

    override suspend fun getValue(key: String): String? {
        return dataStore.getValueOnce(stringPreferencesKey(key), encryption)
    }

    override suspend fun remove(key: String) = dataStore.removeValue(stringPreferencesKey(key))

    override suspend fun clear() = dataStore.clearAll()
}
