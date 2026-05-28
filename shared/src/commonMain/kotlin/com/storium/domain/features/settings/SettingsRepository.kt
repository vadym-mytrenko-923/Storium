package com.storium.domain.features.settings

import com.storium.domain.features.settings.model.AppLanguage
import com.storium.domain.features.settings.model.AppTheme
import kotlinx.coroutines.flow.Flow

interface SettingsRepository {
    val themeFlow: Flow<AppTheme>
    val languageFlow: Flow<AppLanguage>
    suspend fun setTheme(theme: AppTheme)
    suspend fun setLanguage(language: AppLanguage)
}
