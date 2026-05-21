package com.storium

import androidx.compose.runtime.Composable
import com.storium.ui.AppViewModel
import com.storium.ui.core.alert.AppAlertProvider
import com.storium.ui.navigation.app.AppNavHost
import com.storium.ui.theme.StoriumTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App(viewModel: AppViewModel = koinViewModel()) {
    StoriumTheme {
        AppAlertProvider {
            AppNavHost(isUserLoggedInFlow = viewModel.isUserLoggedInFlow)
        }
    }
}
