package com.storium.di

import com.storium.di.app.appModule
import com.storium.di.auth.authModule
import com.storium.di.navigation.navigationModule
import com.storium.di.network.networkModule
import com.storium.di.storage.storageModule
import com.storium.di.user.userModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin

fun initKoin(): KoinApplication = startKoin {
    modules(
        storageModule,
        appModule,
        networkModule,
        navigationModule,
        userModule,
        authModule,
    )
}
