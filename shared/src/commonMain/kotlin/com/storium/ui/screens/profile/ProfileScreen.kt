package com.storium.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storium.ui.core.composable.toolbar.Toolbar
import com.storium.ui.screens.profile.composable.ProfileContent
import com.storium.ui.screens.profile.composable.preview.ProfilePreviewUiModels
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.marginPrimary2X
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.profileTitle

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: ProfileViewModel = koinViewModel(),
) {
    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    ProfileScreenContent(
        state = state,
        onIntent = viewModel::onUserIntent,
        paddingValues = paddingValues,
    )
}

@Composable
private fun ProfileScreenContent(
    modifier: Modifier = Modifier,
    state: ProfileScreenState,
    onIntent: (ProfileIntent) -> Unit,
    paddingValues: PaddingValues = PaddingValues(),
) {
    Column(modifier = modifier.fillMaxSize()) {
        Toolbar(
            title = stringResource(Res.string.profileTitle),
            topPadding = paddingValues.calculateTopPadding() + marginPrimary2X,
        )

        ProfileContent(
            modifier = Modifier.weight(1f),
            state = state,
            onIntent = onIntent,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenContentPreview() {
    StoriumTheme {
        ProfileScreenContent(
            state = ProfileScreenState(
                profile = ProfilePreviewUiModels.profile,
            ),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileScreenContentEmptyPreview() {
    StoriumTheme {
        ProfileScreenContent(
            state = ProfileScreenState(),
            onIntent = {},
        )
    }
}
