package com.storium.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storium.ui.screens.main.composable.MainBottomBar
import com.storium.ui.screens.main.model.MainTab
import com.storium.ui.screens.profile.ProfileScreen
import com.storium.ui.screens.shop.ShopScreen
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun MainScreen(viewModel: MainViewModel = koinViewModel()) {
    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    MainScreenContent(
        state = state,
        onIntent = viewModel::onUserIntent,
    )
}

@Composable
private fun MainScreenContent(
    state: MainScreenState,
    onIntent: (MainIntent) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.appColors.background,
        bottomBar = {
            MainBottomBar(
                selectedTab = state.selectedTab,
                onTabSelected = { onIntent(MainIntent.TabSelected(it)) },
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = paddingValues.calculateTopPadding(),
                    bottom = paddingValues.calculateBottomPadding(),
                ),
        ) {
            when (state.selectedTab) {
                MainTab.Shop -> ShopScreen()
                MainTab.Profile -> ProfileScreen()
            }
        }
    }
}

@Preview
@Composable
private fun MainScreenContentPreview() {
    StoriumTheme {
        MainScreenContent(
            state = MainScreenState(),
            onIntent = {},
        )
    }
}

@Preview
@Composable
private fun MainScreenContentProfilePreview() {
    StoriumTheme {
        MainScreenContent(
            state = MainScreenState(selectedTab = MainTab.Profile),
            onIntent = {},
        )
    }
}
