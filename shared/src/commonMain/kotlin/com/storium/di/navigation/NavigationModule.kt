package com.storium.di.navigation

import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.navigation.main.MainNavigator
import org.koin.dsl.module

val navigationModule = module {
    // Navigators
    single { AppNavigator() }
    single { MainNavigator() }
}
