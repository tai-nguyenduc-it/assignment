package com.crypto.architecture.domain.usecase

import com.crypto.architecture.domain.coroutine.CoroutineContextProvider
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.withContext

abstract class BackgroundExecuteUseCase<in REQUEST, out RESULT> constructor(
    private val coroutineContextProvider: CoroutineContextProvider
) : BaseUseCase<REQUEST, RESULT>() {
    final override suspend fun execute(value: REQUEST, callback: (RESULT) -> Unit) {
        val result = withContext(coroutineContextProvider.io) {
            executeInBackground(value, this)
        }

        callback(result)
    }

    abstract suspend fun executeInBackground(
        request: REQUEST,
        coroutineScope: CoroutineScope
    ): RESULT
}
