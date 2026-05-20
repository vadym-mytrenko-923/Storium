package com.storium.di

import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.navigation.main.MainNavigator
import com.storium.ui.screens.auth.login.LoginViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val uiModule = module {
    single { AppNavigator() }
    single { MainNavigator() }
    viewModel { LoginViewModel(get()) }
}
