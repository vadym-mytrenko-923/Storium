package com.storium

import androidx.compose.runtime.Composable
import com.storium.ui.AppViewModel
import com.storium.ui.core.alert.AppAlertProvider
import com.storium.ui.navigation.app.AppNavHost
import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.theme.StoriumTheme
import org.koin.compose.koinInject
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(viewModel: AppViewModel = koinViewModel()) {
    val appNavigator: AppNavigator = koinInject()

    StoriumTheme {
        AppAlertProvider {
            AppNavHost(
                appNavigator = appNavigator,
                isUserLoggedInFlow = viewModel.isUserLoggedInFlow,
            )
        }
    }
}
