package com.storium.di.app

import com.storium.ui.AppViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    // ViewModels
    viewModel { AppViewModel(get()) }
}
