package com.storium.di.storage

import com.storium.data.local.APP_STORAGE_NAME
import com.storium.data.local.USER_STORAGE_NAME
import com.storium.data.local.storage.createAeadEncryption
import com.storium.data.local.storage.createSecureStorage
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val storageModule = module {
    // Encryption
    single { createAeadEncryption(androidContext()) }

    // Secure Storage
    single(named(USER_STORAGE_NAME)) { createSecureStorage(androidContext(), USER_STORAGE_NAME, get()) }
    single(named(APP_STORAGE_NAME)) { createSecureStorage(androidContext(), APP_STORAGE_NAME, get()) }
}
