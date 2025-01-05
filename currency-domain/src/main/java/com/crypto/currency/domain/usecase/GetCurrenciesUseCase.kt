package com.crypto.currency.domain.usecase

import com.crypto.architecture.domain.coroutine.CoroutineContextProvider
import com.crypto.architecture.domain.usecase.BackgroundExecuteUseCase
import com.crypto.currency.domain.repository.CurrencyRepository
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel
import kotlinx.coroutines.CoroutineScope

abstract class GetCurrenciesUseCase(
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecuteUseCase<Unit, List<CurrencyDomainModel>>(coroutineContextProvider)

class GetCurrenciesUseCaseImpl(
    private val repository: CurrencyRepository,
    coroutineContextProvider: CoroutineContextProvider
) : GetCurrenciesUseCase(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: Unit,
        coroutineScope: CoroutineScope
    ) = repository.currencies()
}
