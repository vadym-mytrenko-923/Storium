package com.storium.di.navigation

import com.storium.ui.navigation.app.AppNavigator
import org.koin.dsl.module

val navigationModule = module {
    // Navigators
    single { AppNavigator() }
}
