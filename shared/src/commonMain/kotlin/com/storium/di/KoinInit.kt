package com.storium.di

import com.storium.di.app.appModule
import com.storium.di.auth.authModule
import com.storium.di.navigation.navigationModule
import com.storium.di.network.networkModule
import com.storium.di.user.userModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.core.module.Module

fun initKoin(platformModules: List<Module> = emptyList()): KoinApplication = startKoin {
    modules(
        platformModules + listOf(
            appModule,
            networkModule,
            navigationModule,
            userModule,
            authModule,
        )
    )
}
