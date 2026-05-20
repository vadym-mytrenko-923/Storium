package com.storium.domain.features.auth.usecase

import com.storium.domain.base.usecase.BaseNoParamsFlowUseCase
import com.storium.domain.features.auth.AuthRepository

class IsUserLoggedInFlowUseCase(private val authRepository: AuthRepository) : BaseNoParamsFlowUseCase<Boolean>() {
    override fun execute() = authRepository.isUserLoggedInFlow
}
