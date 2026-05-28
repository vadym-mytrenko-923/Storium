package com.storium.ui.core.composable.other

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf

interface StatusBarController {
    fun setIcons(isWhiteIcons: Boolean)
}

val LocalStatusBar = staticCompositionLocalOf<StatusBarController> {
    error("No StatusBarController provided")
}

@Composable
expect fun StatusBarProvider(content: @Composable () -> Unit)

@Composable
fun SetStatusBarIconsAppearance(isWhiteIcons: Boolean = true) {
    val statusBar = LocalStatusBar.current
    SideEffect {
        statusBar.setIcons(isWhiteIcons = isWhiteIcons)
    }
}
