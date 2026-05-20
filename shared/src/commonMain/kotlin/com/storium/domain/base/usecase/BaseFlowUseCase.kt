package com.storium.domain.base.usecase

import kotlinx.coroutines.flow.Flow

abstract class BaseFlowUseCase<IN, OUT> {
    operator fun invoke(parameters: IN): Flow<OUT> = execute(parameters)

    protected abstract fun execute(parameters: IN): Flow<OUT>
}
