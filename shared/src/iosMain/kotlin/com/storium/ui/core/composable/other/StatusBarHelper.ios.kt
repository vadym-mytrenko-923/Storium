package com.storium.ui.core.composable.other

import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import platform.UIKit.UIApplication
import platform.UIKit.UIStatusBarStyleDarkContent
import platform.UIKit.UIStatusBarStyleLightContent
import platform.UIKit.setStatusBarStyle

@Composable
actual fun StatusBarProvider(content: @Composable () -> Unit) {
    val controller = object : StatusBarController {
        override fun setIcons(isWhiteIcons: Boolean) {
            val style = if (isWhiteIcons) UIStatusBarStyleLightContent else UIStatusBarStyleDarkContent
            UIApplication.sharedApplication.setStatusBarStyle(style, animated = true)
        }
    }

    CompositionLocalProvider(LocalStatusBar provides controller) {
        content()
    }
}
