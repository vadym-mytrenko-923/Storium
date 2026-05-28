package com.storium.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.storium.domain.features.auth.usecase.IsUserLoggedInFlowUseCase
import com.storium.domain.features.settings.model.AppTheme
import com.storium.domain.features.settings.usecase.GetLanguageFlowUseCase
import com.storium.domain.features.settings.usecase.GetThemeFlowUseCase
import com.storium.util.locale.AppLocaleManager
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class AppViewModel(
    isUserLoggedInFlowUseCase: IsUserLoggedInFlowUseCase,
    getThemeFlowUseCase: GetThemeFlowUseCase,
    private val getLanguageFlowUseCase: GetLanguageFlowUseCase,
    private val localeManager: AppLocaleManager,
) : ViewModel() {

    init {
        observeLanguage()
    }

    val isUserLoggedInFlow: StateFlow<Boolean?> = isUserLoggedInFlowUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null,
    )

    val isDarkThemeFlow: StateFlow<Boolean> = getThemeFlowUseCase().map { it == AppTheme.Dark }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false,
    )

    private fun observeLanguage() {
        viewModelScope.launch {
            getLanguageFlowUseCase().collect { language ->
                localeManager.applyLocale(language)
            }
        }
    }
}
