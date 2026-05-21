package com.storium.di.auth

import com.storium.data.features.auth.AuthRepositoryImpl
import com.storium.domain.features.auth.AuthRepository
import com.storium.domain.features.auth.usecase.IsUserLoggedInFlowUseCase
import com.storium.domain.features.auth.usecase.LogoutUseCase
import com.storium.domain.features.auth.usecase.SetUserLoggedInUseCase
import com.storium.ui.screens.auth.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get()) }

    // Use Cases
    factory { SetUserLoggedInUseCase(get()) }
    factory { IsUserLoggedInFlowUseCase(get()) }
    factory { LogoutUseCase(get()) }

    // ViewModels
    viewModel { LoginViewModel(get()) }
}
