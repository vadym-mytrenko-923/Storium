package com.storium.domain.features.auth.usecase

import com.storium.domain.base.result.useResultWrapper
import com.storium.domain.base.usecase.BaseUseCase
import com.storium.domain.features.auth.AuthRepository
import com.storium.domain.features.auth.model.LoginParams
import com.storium.domain.features.auth.model.User

class LoginUseCase(private val repository: AuthRepository) : BaseUseCase<LoginParams, Result<User>>() {
    override suspend fun execute(parameters: LoginParams): Result<User> = useResultWrapper {
        repository.login(parameters)
    }
}
