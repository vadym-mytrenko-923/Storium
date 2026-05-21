package com.storium.di.storage

import com.storium.data.local.APP_STORAGE_NAME
import com.storium.data.local.USER_STORAGE_NAME
import com.storium.data.local.storage.createSecureStorage
import org.koin.core.qualifier.named
import org.koin.dsl.module

val storageModule = module {
    // Secure Storage
    single(named(USER_STORAGE_NAME)) { createSecureStorage(USER_STORAGE_NAME) }
    single(named(APP_STORAGE_NAME)) { createSecureStorage(APP_STORAGE_NAME) }
}
