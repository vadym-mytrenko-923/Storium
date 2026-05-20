package com.storium.domain.base.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext

abstract class BaseUseCase<IN, OUT> {
    protected open val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend operator fun invoke(parameters: IN): OUT = withContext(dispatcher) { execute(parameters) }

    protected abstract suspend fun execute(parameters: IN): OUT
}
