package com.storium.di

import com.storium.di.app.appModule
import com.storium.di.auth.authModule
import com.storium.di.main.mainModule
import com.storium.di.navigation.navigationModule
import com.storium.di.network.networkModule
import com.storium.di.storage.storageModule
import com.storium.di.user.userModule
import org.koin.core.KoinApplication
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.includes

fun initKoin(config: KoinAppDeclaration? = null): KoinApplication = startKoin {
    includes(config)
    modules(
        storageModule,
        appModule,
        networkModule,
        navigationModule,
        userModule,
        authModule,
        mainModule,
    )
}
