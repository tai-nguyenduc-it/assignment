package com.crypto.currency.domain.usecase

import com.crypto.architecture.domain.coroutine.CoroutineContextProvider
import com.crypto.architecture.domain.usecase.BackgroundExecuteUseCase
import com.crypto.currency.domain.repository.CurrencyRepository
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel
import kotlinx.coroutines.CoroutineScope

abstract class AddCurrenciesUseCase(
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecuteUseCase<List<CurrencyDomainModel>, Unit>(coroutineContextProvider)

class AddCurrenciesUseCaseImpl(
    private val repository: CurrencyRepository,
    coroutineContextProvider: CoroutineContextProvider
) : AddCurrenciesUseCase(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: List<CurrencyDomainModel>,
        coroutineScope: CoroutineScope
    ) = repository.addCurrencies(request)
}
