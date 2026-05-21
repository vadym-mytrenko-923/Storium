package com.storium.domain.base.result

suspend fun <T> useResultWrapper(action: suspend () -> T): Result<T> = try {
    Result.success(action())
} catch (t: Throwable) {
    Result.failure(t)
}
