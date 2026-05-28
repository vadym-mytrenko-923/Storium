package com.storium.domain.features.settings.usecase

import com.storium.domain.base.usecase.BaseNoParamsFlowUseCase
import com.storium.domain.features.settings.SettingsRepository
import com.storium.domain.features.settings.model.AppTheme
import kotlinx.coroutines.flow.Flow

class GetThemeFlowUseCase(private val repository: SettingsRepository) : BaseNoParamsFlowUseCase<AppTheme>() {
    override fun execute(): Flow<AppTheme> = repository.themeFlow
}
