package com.storium.domain.features.auth.usecase

import com.storium.domain.base.usecase.BaseNoParamsUseCase
import com.storium.domain.features.auth.AuthRepository

class LogoutUseCase(
    private val repository: AuthRepository,
) : BaseNoParamsUseCase<Unit>() {
    override suspend fun execute() = repository.logout()
}
