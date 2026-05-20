package com.storium.ui.navigation.model

import kotlinx.serialization.Serializable

sealed interface AppNavRoute {
    @Serializable data object Login : AppNavRoute
    @Serializable data object Main : AppNavRoute
    @Serializable data class ProductDetails(val productId: Int) : AppNavRoute
}

sealed interface MainNavRoute {
    @Serializable data object Products : MainNavRoute
    @Serializable data object Settings : MainNavRoute
}
