package com.crypto.architecture.domain

import com.crypto.architecture.domain.exception.DomainException
import com.crypto.architecture.domain.exception.UnknownDomainException
import com.crypto.architecture.domain.usecase.BaseUseCase
import com.crypto.architecture.domain.usecase.RunningExecution
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

open class UseCaseExecutor(
    private var coroutineScope: CoroutineScope,
    private val jobProvider: () -> Job = { Job() }
) {
    constructor(coroutineScope: CoroutineScope) : this(coroutineScope, { Job() })

    private var job: Job = jobProvider()

    open fun <OUT_TYPE> execute(
        baseUseCase: BaseUseCase<Unit, OUT_TYPE>,
        onResult: (OUT_TYPE) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ) = execute(baseUseCase, Unit, onResult, onException)

    open fun <IN_TYPE, OUT_TYPE> execute(
        baseUseCase: BaseUseCase<IN_TYPE, OUT_TYPE>,
        value: IN_TYPE,
        onResult: (OUT_TYPE) -> Unit = {},
        onException: (DomainException) -> Unit = {}
    ): RunningExecution {
        val job = launchUseCaseExecution(baseUseCase, value, onResult, onException)
        return RunningUseCaseExecution(job)
    }

    private fun <IN_TYPE, OUT_TYPE> launchUseCaseExecution(
        baseUseCase: BaseUseCase<IN_TYPE, OUT_TYPE>,
        value: IN_TYPE,
        onResult: (OUT_TYPE) -> Unit,
        onException: (DomainException) -> Unit
    ): Job {
        val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
            if (throwable.isOfCancellationType().not()) {
                onException(throwable.toDomainException())
            }
        }
        return coroutineScope.launch(job + coroutineExceptionHandler) {
            val callbackWrapper = { result: OUT_TYPE ->
                onResult(result)
            }
            try {
                baseUseCase.execute(value, callbackWrapper)
            } catch (_: CancellationException) {
            } catch (throwable: Throwable) {
                onException(throwable.toDomainException())
            }
        }
    }

    private fun Throwable.toDomainException() = if (this is DomainException) {
        this
    } else {
        UnknownDomainException(message ?: "No message provided", this)
    }

    fun cancelAll() {
        job.cancel()
        job = jobProvider()
    }

    class RunningUseCaseExecution(private val job: Job) : RunningExecution {
        override fun isRunning() = job.isActive

        override fun cancel() {
            job.cancel()
        }
    }
}

private fun Throwable.isOfCancellationType(recursionDepth: Int = 0): Boolean {
    if (this is CancellationException) {
        return true
    }
    if (recursionDepth > 30) return false
    return this.cause?.isOfCancellationType(recursionDepth + 1) == true
}
