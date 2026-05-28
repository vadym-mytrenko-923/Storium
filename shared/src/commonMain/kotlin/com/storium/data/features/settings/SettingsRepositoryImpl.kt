package com.storium.data.features.settings

import com.storium.data.features.settings.local.storage.SettingsStorage
import com.storium.domain.features.settings.SettingsRepository
import com.storium.domain.features.settings.model.AppLanguage
import com.storium.domain.features.settings.model.AppTheme
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingsRepositoryImpl(
    private val settingsStorage: SettingsStorage,
) : SettingsRepository {
    override val themeFlow: Flow<AppTheme> = settingsStorage.themeFlow.map { value ->
        AppTheme.entries.firstOrNull { it.name == value } ?: AppTheme.Light
    }

    override val languageFlow: Flow<AppLanguage> = settingsStorage.languageFlow.map { value ->
        AppLanguage.entries.firstOrNull { it.name == value } ?: AppLanguage.English
    }

    override suspend fun setTheme(theme: AppTheme) = settingsStorage.setTheme(theme.name)

    override suspend fun setLanguage(language: AppLanguage) = settingsStorage.setLanguage(language.name)
}
