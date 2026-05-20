package com.storium.domain.base.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class BaseNoParamsUseCase<OUT> {
    protected open val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend operator fun invoke(): OUT = withContext(dispatcher) { execute() }

    protected abstract suspend fun execute(): OUT
}
