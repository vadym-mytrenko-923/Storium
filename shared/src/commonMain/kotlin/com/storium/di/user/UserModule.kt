package com.storium.di.user

import com.storium.data.features.user.UserRepositoryImpl
import com.storium.data.features.user.local.UserLocalDataSource
import com.storium.data.features.user.local.UserLocalDataSourceImpl
import com.storium.data.features.user.local.storage.UserStorage
import com.storium.data.features.user.local.storage.UserStorageImpl
import com.storium.data.local.storage.USER_STORAGE_NAME
import com.storium.domain.features.user.UserRepository
import com.storium.domain.features.user.usecase.GetUserDataFlowUseCase
import com.storium.ui.screens.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val userModule = module {
    // Storage
    single<UserStorage> { UserStorageImpl(get(named(USER_STORAGE_NAME)), get()) }

    // Data Sources
    single<UserLocalDataSource> { UserLocalDataSourceImpl(get()) }

    // Repositories
    single<UserRepository> { UserRepositoryImpl(get()) }

    // Use Cases
    factory { GetUserDataFlowUseCase(get()) }

    // ViewModels
    viewModel { ProfileViewModel(get(), get()) }
}
