package com.crypto.currency.domain.usecase

import com.crypto.architecture.domain.coroutine.CoroutineContextProvider
import com.crypto.architecture.domain.usecase.BackgroundExecuteUseCase
import com.crypto.currency.domain.repository.CurrencyRepository
import kotlinx.coroutines.CoroutineScope

abstract class DeleteCurrenciesUseCase(
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecuteUseCase<Unit, Unit>(coroutineContextProvider)

class DeleteCurrenciesUseCaseImpl(
    private val repository: CurrencyRepository,
    coroutineContextProvider: CoroutineContextProvider
) : DeleteCurrenciesUseCase(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: Unit,
        coroutineScope: CoroutineScope
    ) = repository.deleteAllCurrencies()
}
