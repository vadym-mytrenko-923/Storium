package com.storium.domain.features.settings.usecase

import com.storium.domain.base.usecase.BaseNoParamsFlowUseCase
import com.storium.domain.features.settings.SettingsRepository
import com.storium.domain.features.settings.model.AppLanguage
import kotlinx.coroutines.flow.Flow

class GetLanguageFlowUseCase(private val repository: SettingsRepository) : BaseNoParamsFlowUseCase<AppLanguage>() {
    override fun execute(): Flow<AppLanguage> = repository.languageFlow
}
