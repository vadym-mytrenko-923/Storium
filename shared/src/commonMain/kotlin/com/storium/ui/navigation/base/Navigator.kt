package com.storium.ui.navigation.base

import com.storium.ui.navigation.model.NavigationEffect
import kotlinx.coroutines.flow.Flow

interface Navigator<R> {
    val navigationEffect: Flow<NavigationEffect<R>>
    suspend fun navigateTo(route: R)
    suspend fun returnTo(route: R)
    suspend fun back()
}
