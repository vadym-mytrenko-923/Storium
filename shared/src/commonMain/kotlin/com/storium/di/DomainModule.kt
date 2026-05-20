package com.storium.di

import com.storium.domain.features.auth.usecase.IsUserLoggedInFlowUseCase
import com.storium.domain.features.auth.usecase.LogoutUseCase
import com.storium.domain.features.auth.usecase.SetUserLoggedInUseCase
import org.koin.dsl.module

val domainModule = module {
    factory { SetUserLoggedInUseCase(get()) }
    factory { IsUserLoggedInFlowUseCase(get()) }
    factory { LogoutUseCase(get()) }
}
