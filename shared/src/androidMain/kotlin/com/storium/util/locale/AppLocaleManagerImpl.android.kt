package com.storium.util.locale

import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.storium.domain.features.settings.model.AppLanguage

class AppLocaleManagerImpl : AppLocaleManager {
    override fun applyLocale(language: AppLanguage) {
        AppCompatDelegate.setApplicationLocales(LocaleListCompat.forLanguageTags(language.code))
    }
}
