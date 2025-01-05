package com.crypto.architecture.domain.usecase

import com.crypto.architecture.domain.UseCaseExecutor
import com.crypto.architecture.domain.exception.DomainException

abstract class BaseUseCase<in REQUEST, out RESULT> {
    fun execute(
        useCaseExecutor: UseCaseExecutor,
        value: REQUEST,
        onResult: (RESULT) -> Unit,
        onException: (DomainException) -> Unit = {}
    ): Cancellable = useCaseExecutor.execute(this, value, onResult, onException)

    abstract suspend fun execute(value: REQUEST, callback: (RESULT) -> Unit)
}

fun <RESULT : Any> BaseUseCase<Unit, RESULT>.execute(
    useCaseExecutor: UseCaseExecutor,
    onResult: (RESULT) -> Unit,
    onException: (DomainException) -> Unit = {}
) {
    useCaseExecutor.execute(this, onResult, onException)
}
