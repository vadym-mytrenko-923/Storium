package com.storium.ui.screens.main

import com.storium.ui.base.BaseViewModel

class MainViewModel : BaseViewModel<MainScreenState, MainIntent, MainEffect>(MainScreenState()) {
    override suspend fun reduceIntent(intent: MainIntent) {
        when (intent) {
            is MainIntent.TabSelected -> updateUiState { copy(selectedTab = intent.tab) }
        }
    }
}
