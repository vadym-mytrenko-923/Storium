package com.storium.di

import com.storium.domain.features.auth.usecase.IsUserLoggedInUseCase
import com.storium.domain.features.auth.usecase.LogoutUseCase
import com.storium.domain.features.auth.usecase.SetUserLoggedInUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { SetUserLoggedInUseCase(get()) }
    factory { IsUserLoggedInUseCase(get()) }
    factory { LogoutUseCase(get()) }
}
