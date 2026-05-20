package com.storium.ui.navigation.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.navigation.NavController
import com.storium.ui.navigation.model.NavigationEffect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun <R : Any> NavigationEffectHandler(
    navigator: Navigator<R>,
    navController: NavController,
) {
    LaunchedEffect(navigator) {
        navigator.navigationEffect.collectLatest { effect ->
            when (effect) {
                is NavigationEffect.Navigate -> navController.navigate(effect.route)
                is NavigationEffect.ReturnTo -> navController.popBackStack(
                    route = effect.route,
                    inclusive = false,
                )
                is NavigationEffect.Back -> navController.popBackStack()
            }
        }
    }
}
