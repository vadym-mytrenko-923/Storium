package com.storium.data.features.settings.local.storage

import kotlinx.coroutines.flow.Flow

interface SettingsStorage {
    val themeFlow: Flow<String?>
    val languageFlow: Flow<String?>
    suspend fun setTheme(theme: String)
    suspend fun setLanguage(language: String)
}
