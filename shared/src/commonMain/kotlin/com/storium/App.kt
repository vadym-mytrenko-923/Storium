package com.storium

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storium.ui.AppViewModel
import com.storium.ui.core.alert.AppAlertProvider
import com.storium.ui.core.composable.other.SetStatusBarIconsAppearance
import com.storium.ui.core.composable.other.StatusBarProvider
import com.storium.ui.navigation.app.AppNavHost
import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.theme.StoriumTheme
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(viewModel: AppViewModel = koinViewModel()) {
    val appNavigator: AppNavigator = koinInject()
    val isDarkTheme by viewModel.isDarkThemeFlow.collectAsStateWithLifecycle()

    StoriumTheme(darkTheme = isDarkTheme) {
        StatusBarProvider {
            SetStatusBarIconsAppearance(isWhiteIcons = isDarkTheme)

            AppAlertProvider {
                AppNavHost(
                    appNavigator = appNavigator,
                    isUserLoggedInFlow = viewModel.isUserLoggedInFlow,
                )
            }
        }
    }
}
