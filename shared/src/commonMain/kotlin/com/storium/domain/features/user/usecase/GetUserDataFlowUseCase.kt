package com.storium.domain.features.user.usecase

import com.storium.domain.base.usecase.BaseNoParamsFlowUseCase
import com.storium.domain.features.auth.model.User
import com.storium.domain.features.user.UserRepository
import kotlinx.coroutines.flow.Flow

class GetUserDataFlowUseCase(private val repository: UserRepository) : BaseNoParamsFlowUseCase<User?>() {
    override fun execute(): Flow<User?> = repository.userDataFlow
}
