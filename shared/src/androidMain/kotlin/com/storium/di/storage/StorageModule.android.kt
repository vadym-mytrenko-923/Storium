package com.storium.di.storage

import androidx.datastore.core.DataStore
import androidx.datastore.core.handlers.ReplaceFileCorruptionHandler
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStoreFile
import com.storium.data.local.APP_STORAGE_NAME
import com.storium.data.local.SecureStorage
import com.storium.data.local.USER_STORAGE_NAME
import com.storium.data.local.storage.DataStoreSecureStorage
import com.storium.data.local.storage.createAeadEncryption
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val storageModule = module {
    // Encryption
    single { createAeadEncryption(androidContext()) }

    // DataStores
    single<DataStore<Preferences>>(named(USER_STORAGE_NAME)) {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            produceFile = { androidContext().preferencesDataStoreFile(USER_STORAGE_NAME) },
        )
    }

    single<DataStore<Preferences>>(named(APP_STORAGE_NAME)) {
        PreferenceDataStoreFactory.create(
            corruptionHandler = ReplaceFileCorruptionHandler { emptyPreferences() },
            produceFile = { androidContext().preferencesDataStoreFile(APP_STORAGE_NAME) },
        )
    }

    // Secure Storage
    single<SecureStorage>(named(USER_STORAGE_NAME)) {
        DataStoreSecureStorage(get(named(USER_STORAGE_NAME)), get())
    }

    single<SecureStorage>(named(APP_STORAGE_NAME)) {
        DataStoreSecureStorage(get(named(APP_STORAGE_NAME)), get())
    }
}
