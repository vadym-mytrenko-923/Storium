package com.storium

import androidx.compose.ui.window.ComposeUIViewController
import com.storium.di.initKoinIos

fun MainViewController() = ComposeUIViewController(
    configure = { initKoinIos() },
) { App() }
