package com.storium.di.locale

import com.storium.util.locale.AppLocaleManager
import com.storium.util.locale.AppLocaleManagerImpl
import org.koin.dsl.module

actual val localeModule = module {
    single<AppLocaleManager> { AppLocaleManagerImpl() }
}
