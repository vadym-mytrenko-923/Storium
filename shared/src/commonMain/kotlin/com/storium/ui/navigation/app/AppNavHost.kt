package com.storium.ui.navigation.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.storium.domain.features.auth.usecase.IsUserLoggedInUseCase
import com.storium.ui.navigation.model.AppNavRoute
import com.storium.ui.screens.auth.login.LoginScreen
import com.storium.ui.screens.main.MainScreen
import org.koin.compose.koinInject

@Composable
fun AppNavHost(isUserLoggedInUseCase: IsUserLoggedInUseCase = koinInject()) {
    val navController = rememberNavController()
    val isUserLoggedIn by isUserLoggedInUseCase().collectAsState(initial = false)
    var previousLoginState by rememberSaveable { mutableStateOf(false) }

    val startDestination = remember {
        if (isUserLoggedIn) AppNavRoute.Main else AppNavRoute.Login
    }

    LaunchedEffect(isUserLoggedIn) {
        if (isUserLoggedIn != previousLoginState) {
            previousLoginState = isUserLoggedIn
            val targetRoute = if (isUserLoggedIn) AppNavRoute.Main else AppNavRoute.Login
            navController.navigate(targetRoute) {
                popUpTo(0) { inclusive = true }
                launchSingleTop = true
            }
        }
    }

    NavHost(navController = navController, startDestination = startDestination) {
        composable<AppNavRoute.Login> {
            LoginScreen()
        }
        composable<AppNavRoute.Main> {
            MainScreen()
        }
    }
}
