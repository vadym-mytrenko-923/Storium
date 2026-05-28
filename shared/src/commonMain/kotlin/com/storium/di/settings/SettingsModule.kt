package com.storium.di.settings

import com.storium.data.features.settings.SettingsRepositoryImpl
import com.storium.data.features.settings.local.storage.SettingsStorage
import com.storium.data.features.settings.local.storage.SettingsStorageImpl
import com.storium.data.local.storage.APP_STORAGE_NAME
import com.storium.domain.features.settings.SettingsRepository
import com.storium.domain.features.settings.usecase.GetLanguageFlowUseCase
import com.storium.domain.features.settings.usecase.GetThemeFlowUseCase
import com.storium.domain.features.settings.usecase.SetLanguageUseCase
import com.storium.domain.features.settings.usecase.SetThemeUseCase
import com.storium.ui.screens.settings.SettingsViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val settingsModule = module {
    // Storage
    single<SettingsStorage> { SettingsStorageImpl(get(named(APP_STORAGE_NAME))) }

    // Repositories
    single<SettingsRepository> { SettingsRepositoryImpl(get()) }

    // Use Cases
    factory { GetThemeFlowUseCase(get()) }
    factory { GetLanguageFlowUseCase(get()) }
    factory { SetThemeUseCase(get()) }
    factory { SetLanguageUseCase(get()) }

    // ViewModels
    viewModel { SettingsViewModel(get(), get(), get(), get(), get()) }
}
