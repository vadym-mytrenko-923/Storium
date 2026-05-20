package com.storium.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences

fun createUserDataStore(context: Context): DataStore<Preferences> = createDataStore(
    path = context.filesDir.resolve(USER_STORAGE_NAME).absolutePath
)
