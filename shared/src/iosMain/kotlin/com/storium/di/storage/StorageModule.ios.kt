package com.storium.di.storage

import com.storium.data.local.storage.APP_STORAGE_NAME
import com.storium.data.local.storage.KeychainProvider
import com.storium.data.local.storage.KeychainProviderImpl
import com.storium.data.local.storage.KeychainSecureStorage
import com.storium.data.local.storage.SecureStorage
import com.storium.data.local.storage.USER_STORAGE_NAME
import org.koin.core.qualifier.named
import org.koin.dsl.module

actual val storageModule = module {
    // Keychain Providers
    single<KeychainProvider>(named(USER_STORAGE_NAME)) { KeychainProviderImpl(USER_STORAGE_NAME) }
    single<KeychainProvider>(named(APP_STORAGE_NAME)) { KeychainProviderImpl(APP_STORAGE_NAME) }

    // Secure Storage
    single<SecureStorage>(named(USER_STORAGE_NAME)) { KeychainSecureStorage(get(named(USER_STORAGE_NAME))) }
    single<SecureStorage>(named(APP_STORAGE_NAME)) { KeychainSecureStorage(get(named(APP_STORAGE_NAME))) }
}
