package com.storium.domain.features.auth.usecase

import com.storium.domain.base.usecase.BaseNoParamsFlowUseCase
import com.storium.domain.features.auth.AuthRepository
import kotlinx.coroutines.flow.Flow

class IsUserLoggedInUseCase(
    private val repository: AuthRepository,
) : BaseNoParamsFlowUseCase<Boolean>() {
    override fun execute(): Flow<Boolean> = repository.isLoggedInFlow
}
