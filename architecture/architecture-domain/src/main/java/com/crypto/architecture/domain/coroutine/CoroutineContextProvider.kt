package com.crypto.architecture.domain.coroutine

import kotlin.coroutines.CoroutineContext

interface CoroutineContextProvider {
    val main: CoroutineContext
    val io: CoroutineContext
}
