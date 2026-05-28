package com.storium.data.features.settings.local.storage

import com.storium.data.local.storage.SecureStorage
import kotlinx.coroutines.flow.Flow

private const val KEY_THEME = "app_theme"
private const val KEY_LANGUAGE = "app_language"

class SettingsStorageImpl(private val storage: SecureStorage) : SettingsStorage {
    override val themeFlow: Flow<String?> = storage.getFlowValue(KEY_THEME)
    override val languageFlow: Flow<String?> = storage.getFlowValue(KEY_LANGUAGE)
    override suspend fun setTheme(theme: String) = storage.putValue(KEY_THEME, theme)
    override suspend fun setLanguage(language: String) = storage.putValue(KEY_LANGUAGE, language)
}
