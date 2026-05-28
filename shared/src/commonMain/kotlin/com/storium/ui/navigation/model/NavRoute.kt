package com.storium.ui.navigation.model

import kotlinx.serialization.Serializable

sealed interface AppNavRoute {
    @Serializable data object Login : AppNavRoute
    @Serializable data object Main : AppNavRoute
    @Serializable data class ProductDetails(val productId: Int) : AppNavRoute
    @Serializable data object PersonalInfo : AppNavRoute
    @Serializable data object Settings : AppNavRoute
}
