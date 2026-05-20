package com.storium.domain.features.auth.usecase

import com.storium.domain.base.usecase.BaseNoParamsUseCase
import com.storium.domain.features.auth.AuthRepository

class SetUserLoggedInUseCase(private val repository: AuthRepository) : BaseNoParamsUseCase<Unit>() {
    override suspend fun execute() = repository.setLoggedIn(true)
}
