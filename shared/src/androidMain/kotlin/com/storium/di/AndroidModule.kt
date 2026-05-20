package com.storium.di

import com.storium.data.local.createUserDataStore
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single { createUserDataStore(androidContext()) }
}
