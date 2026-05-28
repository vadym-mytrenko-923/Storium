package com.storium.ui.screens.settings.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.core.composable.other.SelectableOptionChip
import com.storium.ui.screens.settings.SettingsIntent
import com.storium.ui.screens.settings.SettingsScreenState
import com.storium.ui.screens.settings.model.LanguageUiModel
import com.storium.ui.screens.settings.model.ThemeUiModel
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.marginPrimary4X
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.settingsLanguageTitle
import storium.shared.generated.resources.settingsThemeTitle

@Composable
fun SettingsContent(
    modifier: Modifier = Modifier,
    state: SettingsScreenState,
    onIntent: (SettingsIntent) -> Unit,
) {
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(horizontal = marginPrimary2X),
        verticalArrangement = Arrangement.spacedBy(marginPrimary4X),
    ) {
        SettingsSection(title = stringResource(Res.string.settingsThemeTitle)) {
            ThemeUiModel.entries.forEach { theme ->
                SelectableOptionChip(
                    text = stringResource(theme.labelRes),
                    isSelected = state.selectedTheme == theme,
                    leadingContent = {
                        Image(
                            modifier = Modifier.size(defaultIconSize),
                            painter = painterResource(theme.iconRes),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.appColors.iconPrimary),
                        )
                    },
                    onClick = { onIntent(SettingsIntent.ThemeSelected(theme)) },
                )
            }
        }

        SettingsSection(title = stringResource(Res.string.settingsLanguageTitle)) {
            LanguageUiModel.entries.forEach { language ->
                SelectableOptionChip(
                    text = stringResource(language.labelRes),
                    isSelected = state.selectedLanguage == language,
                    leadingContent = {
                        Image(
                            modifier = Modifier.size(defaultIconSize),
                            painter = painterResource(language.iconRes),
                            contentDescription = null,
                        )
                    },
                    onClick = { onIntent(SettingsIntent.LanguageSelected(language)) },
                )
            }
        }
    }
}

@Composable
private fun SettingsSection(
    title: String,
    content: @Composable () -> Unit,
) {
    Column(verticalArrangement = Arrangement.spacedBy(marginPrimary2X)) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.appColors.textPrimary,
        )

        Column(verticalArrangement = Arrangement.spacedBy(marginPrimary)) {
            content()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsContentPreview() {
    StoriumTheme {
        SettingsContent(
            state = SettingsScreenState(),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SettingsContentDarkSpanishPreview() {
    StoriumTheme {
        SettingsContent(
            state = SettingsScreenState(
                selectedTheme = ThemeUiModel.Dark,
                selectedLanguage = LanguageUiModel.Spanish,
            ),
            onIntent = {},
        )
    }
}
