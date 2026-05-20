package com.storium.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import okio.Path.Companion.toPath

internal const val USER_STORAGE_NAME = "user_storage.preferences_pb"

fun createDataStore(path: String): DataStore<Preferences> = PreferenceDataStoreFactory.createWithPath(
    produceFile = { path.toPath() }
)
