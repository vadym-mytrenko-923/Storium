package com.storium.domain.features.settings.usecase

import com.storium.domain.base.usecase.BaseUseCase
import com.storium.domain.features.settings.SettingsRepository
import com.storium.domain.features.settings.model.AppTheme

class SetThemeUseCase(private val repository: SettingsRepository) : BaseUseCase<AppTheme, Unit>() {
    override suspend fun execute(parameters: AppTheme) = repository.setTheme(parameters)
}
