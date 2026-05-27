package com.storium.di.app

import com.storium.data.local.storage.APP_STORAGE_NAME
import com.storium.data.local.storage.app.AppStorage
import com.storium.data.local.storage.app.AppStorageImpl
import com.storium.data.system.logger.AppLoggerImpl
import com.storium.domain.system.logger.AppLogger
import com.storium.ui.AppViewModel
import com.storium.ui.core.error.UiErrorParser
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    // Serialization
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    // Storage
    single<AppStorage> { AppStorageImpl(get(named(APP_STORAGE_NAME))) }

    // Logging
    single<AppLogger> { AppLoggerImpl() }

    // Errors handling
    single { UiErrorParser() }

    // ViewModels
    viewModel { AppViewModel(get()) }
}
