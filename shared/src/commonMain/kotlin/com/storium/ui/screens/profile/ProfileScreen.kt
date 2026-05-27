package com.storium.ui.screens.profile

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storium.ui.screens.profile.composable.ProfileContent
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    ProfileContent(
        state = state,
        onIntent = viewModel::onUserIntent,
        paddingValues = paddingValues,
    )
}
