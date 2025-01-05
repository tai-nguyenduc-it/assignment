package com.crypto.architecture.domain

import kotlinx.coroutines.CoroutineScope

typealias UseCaseExecutorProvider = @JvmSuppressWildcards (coroutineScope: CoroutineScope) -> UseCaseExecutor
