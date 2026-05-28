package com.storium.ui.screens.settings.mapper

import com.storium.domain.features.settings.model.AppLanguage
import com.storium.ui.screens.settings.model.LanguageUiModel

fun AppLanguage.toUiModel(): LanguageUiModel = when (this) {
    AppLanguage.English -> LanguageUiModel.English
    AppLanguage.Spanish -> LanguageUiModel.Spanish
}

fun LanguageUiModel.toDomainModel(): AppLanguage = when (this) {
    LanguageUiModel.English -> AppLanguage.English
    LanguageUiModel.Spanish -> AppLanguage.Spanish
}
