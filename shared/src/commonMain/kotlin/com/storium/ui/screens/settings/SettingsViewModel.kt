package com.storium.ui.screens.settings

import com.storium.domain.features.settings.usecase.GetLanguageFlowUseCase
import com.storium.domain.features.settings.usecase.GetThemeFlowUseCase
import com.storium.domain.features.settings.usecase.SetLanguageUseCase
import com.storium.domain.features.settings.usecase.SetThemeUseCase
import com.storium.ui.base.BaseViewModel
import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.screens.settings.mapper.toDomainModel
import com.storium.ui.screens.settings.mapper.toUiModel
import kotlinx.coroutines.flow.combine

class SettingsViewModel(
    private val appNavigator: AppNavigator,
    private val getThemeFlowUseCase: GetThemeFlowUseCase,
    private val getLanguageFlowUseCase: GetLanguageFlowUseCase,
    private val setThemeUseCase: SetThemeUseCase,
    private val setLanguageUseCase: SetLanguageUseCase,
) : BaseViewModel<SettingsScreenState, SettingsIntent, SettingsEffect>(SettingsScreenState()) {

    init {
        observeSettings()
    }

    override suspend fun reduceIntent(intent: SettingsIntent) {
        when (intent) {
            is SettingsIntent.BackClicked -> appNavigator.back()
            is SettingsIntent.ThemeSelected -> setThemeUseCase(intent.theme.toDomainModel())
            is SettingsIntent.LanguageSelected -> setLanguageUseCase(intent.language.toDomainModel())
        }
    }

    private fun observeSettings() {
        launchViewModelScope {
            combine(
                getThemeFlowUseCase(),
                getLanguageFlowUseCase(),
            ) { theme, language ->
                currentState.copy(
                    selectedTheme = theme.toUiModel(),
                    selectedLanguage = language.toUiModel(),
                )
            }.collect { state ->
                updateUiState { state }
            }
        }
    }
}
