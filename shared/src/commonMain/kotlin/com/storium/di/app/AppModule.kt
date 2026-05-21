package com.storium.di.app

import com.storium.data.system.logger.AppLoggerImpl
import com.storium.domain.system.logger.AppLogger
import com.storium.ui.AppViewModel
import com.storium.ui.core.error.UiErrorParser
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // Serialization
    single {
        Json {
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    // Logging
    single<AppLogger> { AppLoggerImpl() }

    // Errors handling
    single { UiErrorParser() }

    // ViewModels
    viewModel { AppViewModel(get()) }
}
