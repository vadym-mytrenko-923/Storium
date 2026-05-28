package com.storium.domain.features.user.usecase

import com.storium.domain.base.usecase.BaseUseCase
import com.storium.domain.features.user.UserRepository

class UpdateUserDataUseCase(private val repository: UserRepository) : BaseUseCase<UpdateUserDataUseCase.Params, Unit>() {

    data class Params(
        val firstName: String,
        val lastName: String,
        val email: String,
    )

    override suspend fun execute(parameters: Params) {
        repository.updateUserData(
            firstName = parameters.firstName,
            lastName = parameters.lastName,
            email = parameters.email,
        )
    }
}
