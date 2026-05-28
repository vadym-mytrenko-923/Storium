package com.storium.ui.screens.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storium.ui.core.composable.toolbar.Toolbar
import com.storium.ui.screens.settings.composable.SettingsContent
import com.storium.ui.screens.settings.model.ThemeUiModel
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary2X
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.settingsTitle

@Composable
fun SettingsScreen(viewModel: SettingsViewModel = koinViewModel()) {
    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    SettingsScreenContent(
        state = state,
        onIntent = viewModel::onUserIntent,
    )
}

@Composable
private fun SettingsScreenContent(
    modifier: Modifier = Modifier,
    state: SettingsScreenState,
    onIntent: (SettingsIntent) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.appColors.background,
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
            verticalArrangement = Arrangement.spacedBy(marginPrimary),
        ) {
            Toolbar(
                title = stringResource(Res.string.settingsTitle),
                topPadding = paddingValues.calculateTopPadding() + marginPrimary2X,
                leadingContent = {
                    Image(
                        modifier = Modifier
                            .size(defaultIconSize)
                            .clickable { onIntent(SettingsIntent.BackClicked) },
                        painter = painterResource(AppIcons.ChevronLeft),
                        contentDescription = null,
                        colorFilter = ColorFilter.tint(MaterialTheme.appColors.iconPrimary),
                    )
                },
            )

            SettingsContent(
                modifier = Modifier.weight(1f),
                state = state,
                onIntent = onIntent,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenContentPreview() {
    StoriumTheme {
        SettingsScreenContent(
            state = SettingsScreenState(),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsScreenContentDarkPreview() {
    StoriumTheme {
        SettingsScreenContent(
            state = SettingsScreenState(selectedTheme = ThemeUiModel.Dark),
            onIntent = {},
        )
    }
}
