package com.storium.util.locale

import com.storium.domain.features.settings.model.AppLanguage

interface AppLocaleManager {
    fun applyLocale(language: AppLanguage)
}
