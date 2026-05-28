package com.storium.util.locale

import com.storium.domain.features.settings.model.AppLanguage
import platform.Foundation.NSUserDefaults

class AppLocaleManagerImpl : AppLocaleManager {
    override fun applyLocale(language: AppLanguage) {
        NSUserDefaults.standardUserDefaults.setObject(listOf(language.code), forKey = "AppleLanguages")
        NSUserDefaults.standardUserDefaults.synchronize()
    }
}
