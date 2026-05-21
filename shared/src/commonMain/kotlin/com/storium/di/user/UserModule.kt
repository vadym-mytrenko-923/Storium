package com.storium.di.user

import com.storium.data.features.user.local.UserLocalDataSource
import com.storium.data.features.user.local.UserLocalDataSourceImpl
import com.storium.data.local.USER_STORAGE_NAME
import com.storium.data.local.storage.user.UserStorage
import com.storium.data.local.storage.user.UserStorageImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module

val userModule = module {
    // Storage
    single<UserStorage> { UserStorageImpl(get(named(USER_STORAGE_NAME)), get()) }

    // Data Sources
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }
}
