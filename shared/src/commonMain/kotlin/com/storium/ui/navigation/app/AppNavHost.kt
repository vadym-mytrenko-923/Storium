package com.storium.ui.navigation.app

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.storium.ui.navigation.model.AppNavRoute
import com.storium.ui.screens.auth.login.LoginScreen
import com.storium.ui.screens.main.MainScreen
import kotlinx.coroutines.flow.StateFlow

@Composable
fun AppNavHost(isUserLoggedInFlow: StateFlow<Boolean?>) {
    val isUserLoggedIn by isUserLoggedInFlow.collectAsStateWithLifecycle()
    val isLoggedIn = isUserLoggedIn ?: return
    val navController = rememberNavController()

    val startDestination: AppNavRoute = remember {
        if (isUserLoggedInFlow.value == true) AppNavRoute.Main else AppNavRoute.Login
    }

    var previousLoginState by rememberSaveable { mutableStateOf(startDestination == AppNavRoute.Main) }
    LaunchedEffect(isLoggedIn) {
        if (isLoggedIn != previousLoginState) {
            previousLoginState = isLoggedIn
            val targetRoute: AppNavRoute = if (isLoggedIn) AppNavRoute.Main else AppNavRoute.Login
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
