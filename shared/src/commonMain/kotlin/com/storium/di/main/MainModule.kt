package com.storium.di.main

import com.storium.ui.screens.main.MainViewModel
import com.storium.ui.screens.profile.ProfileViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val mainModule = module {
    // ViewModels
    viewModel { MainViewModel() }
    viewModel { ProfileViewModel(get()) }
}
