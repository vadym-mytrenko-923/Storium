package com.storium.ui.screens.settings

import com.storium.ui.screens.settings.model.LanguageUiModel
import com.storium.ui.screens.settings.model.ThemeUiModel

data class SettingsScreenState(
    val selectedTheme: ThemeUiModel = ThemeUiModel.Light,
    val selectedLanguage: LanguageUiModel = LanguageUiModel.English,
)

sealed interface SettingsIntent {
    data object BackClicked : SettingsIntent
    data class ThemeSelected(val theme: ThemeUiModel) : SettingsIntent
    data class LanguageSelected(val language: LanguageUiModel) : SettingsIntent
}

sealed interface SettingsEffect
