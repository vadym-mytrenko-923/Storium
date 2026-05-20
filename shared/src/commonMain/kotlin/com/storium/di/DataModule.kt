package com.storium.di

import com.storium.data.features.auth.AuthRepositoryImpl
import com.storium.data.features.auth.local.AuthLocalDataSource
import com.storium.data.features.auth.local.AuthLocalDataSourceImpl
import com.storium.domain.features.auth.AuthRepository
import org.koin.dsl.module

val dataModule = module {
    single<AuthLocalDataSource> { AuthLocalDataSourceImpl() }
    single<AuthRepository> { AuthRepositoryImpl(get()) }
}
