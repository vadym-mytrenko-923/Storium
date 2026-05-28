package com.storium.ui.screens.settings.mapper

import com.storium.domain.features.settings.model.AppTheme
import com.storium.ui.screens.settings.model.ThemeUiModel

fun AppTheme.toUiModel(): ThemeUiModel = when (this) {
    AppTheme.Light -> ThemeUiModel.Light
    AppTheme.Dark -> ThemeUiModel.Dark
}

fun ThemeUiModel.toDomainModel(): AppTheme = when (this) {
    ThemeUiModel.Light -> AppTheme.Light
    ThemeUiModel.Dark -> AppTheme.Dark
}
