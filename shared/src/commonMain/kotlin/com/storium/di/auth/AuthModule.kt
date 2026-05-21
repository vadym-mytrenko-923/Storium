package com.storium.di.auth

import com.storium.data.features.auth.AuthRepositoryImpl
import com.storium.data.features.auth.remote.source.AuthRemoteDataSource
import com.storium.data.features.auth.remote.source.AuthRemoteDataSourceImpl
import com.storium.domain.features.auth.AuthRepository
import com.storium.domain.features.auth.usecase.IsUserLoggedInFlowUseCase
import com.storium.domain.features.auth.usecase.LoginUseCase
import com.storium.domain.features.auth.usecase.LogoutUseCase
import com.storium.domain.features.auth.usecase.SetUserLoggedInUseCase
import com.storium.ui.screens.auth.login.LoginValidator
import com.storium.ui.screens.auth.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val authModule = module {
    // Data Sources
    single<AuthRemoteDataSource> { AuthRemoteDataSourceImpl(get()) }

    // Repositories
    single<AuthRepository> { AuthRepositoryImpl(get(), get()) }

    // Use Cases
    factory { LoginUseCase(get()) }
    factory { SetUserLoggedInUseCase(get()) }
    factory { IsUserLoggedInFlowUseCase(get()) }
    factory { LogoutUseCase(get()) }

    // Validation
    factory { LoginValidator() }

    // ViewModels
    viewModel { LoginViewModel(get(), get(), get(), get()) }
}
