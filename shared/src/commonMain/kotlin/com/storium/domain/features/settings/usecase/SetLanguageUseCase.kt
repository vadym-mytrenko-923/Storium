package com.storium.domain.features.settings.usecase

import com.storium.domain.base.usecase.BaseUseCase
import com.storium.domain.features.settings.SettingsRepository
import com.storium.domain.features.settings.model.AppLanguage

class SetLanguageUseCase(private val repository: SettingsRepository) : BaseUseCase<AppLanguage, Unit>() {
    override suspend fun execute(parameters: AppLanguage) = repository.setLanguage(parameters)
}
