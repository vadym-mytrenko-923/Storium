package com.storium.di

import com.storium.data.local.createUserDataStore
import org.koin.dsl.module

val iosModule = module {
    single { createUserDataStore() }
}
