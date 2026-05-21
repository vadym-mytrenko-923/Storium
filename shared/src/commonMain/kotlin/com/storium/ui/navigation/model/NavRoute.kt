package com.storium.ui.navigation.model

import kotlinx.serialization.Serializable

sealed interface AppNavRoute {
    @Serializable data object Login : AppNavRoute
    @Serializable data object Main : AppNavRoute
}

sealed interface MainNavRoute {
    @Serializable data object Shop : MainNavRoute
    @Serializable data object Profile : MainNavRoute
}
