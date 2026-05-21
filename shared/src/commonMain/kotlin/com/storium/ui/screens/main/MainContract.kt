package com.storium.ui.screens.main

import com.storium.ui.screens.main.model.MainTab

data class MainScreenState(
    val selectedTab: MainTab = MainTab.Shop,
)

sealed interface MainIntent {
    data class TabSelected(val tab: MainTab) : MainIntent
}

sealed interface MainEffect
