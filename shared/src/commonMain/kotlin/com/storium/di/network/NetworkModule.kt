package com.storium.di.network

import com.storium.data.features.auth.remote.api.AuthApi
import com.storium.data.features.auth.remote.api.AuthApiImpl
import com.storium.data.remote.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    // Networking client
    single { createHttpClient(get(), get()) }

    // APIs
    single<AuthApi> { AuthApiImpl(get()) }
}
