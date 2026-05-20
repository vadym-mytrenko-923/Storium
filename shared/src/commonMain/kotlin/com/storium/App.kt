package com.storium

import androidx.compose.runtime.Composable
import com.storium.ui.navigation.app.AppNavHost
import com.storium.ui.theme.StoriumTheme

@Composable
fun App() {
    StoriumTheme {
        AppNavHost()
    }
}
