package com.storium.ui.core.composable.other

import android.app.Activity
import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private class AndroidStatusBarController(
    private val activity: Activity,
    private val viewProvider: () -> View,
) : StatusBarController {
    override fun setIcons(isWhiteIcons: Boolean) {
        val controller = WindowCompat.getInsetsController(activity.window, viewProvider())
        controller.isAppearanceLightStatusBars = !isWhiteIcons
    }
}

@Composable
actual fun StatusBarProvider(content: @Composable () -> Unit) {
    val view = LocalView.current
    val activity = view.context as? Activity

    val controller = if (activity != null && !view.isInEditMode) {
        AndroidStatusBarController(activity) { view }
    } else {
        object : StatusBarController {
            override fun setIcons(isWhiteIcons: Boolean) = Unit
        }
    }

    CompositionLocalProvider(LocalStatusBar provides controller) {
        content()
    }
}
