package com.storium.di

import com.storium.di.app.appModule
import com.storium.di.auth.authModule
import com.storium.di.database.databaseModule
import com.storium.di.locale.localeModule
import com.storium.di.main.mainModule
import com.storium.di.navigation.navigationModule
import com.storium.di.network.networkModule
import com.storium.di.product.productModule
import com.storium.di.settings.settingsModule
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
        databaseModule,
        localeModule,
        appModule,
        networkModule,
        navigationModule,
        settingsModule,
        userModule,
        authModule,
        productModule,
        mainModule,
    )
}
