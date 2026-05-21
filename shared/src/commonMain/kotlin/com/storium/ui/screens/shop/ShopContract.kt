package com.storium.ui.screens.shop

data class ShopScreenState(
    val isLoading: Boolean = false,
)

sealed interface ShopIntent

sealed interface ShopEffect
