package com.storium.di

import com.storium.data.remote.createHttpClient
import org.koin.dsl.module

val networkModule = module {
    single { createHttpClient() }
}
