package com.crypto.currency.domain.usecase

import com.crypto.architecture.domain.coroutine.CoroutineContextProvider
import com.crypto.architecture.domain.usecase.BackgroundExecuteUseCase
import com.crypto.currency.domain.repository.CurrencyRepository
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel
import kotlinx.coroutines.CoroutineScope

abstract class SearchCurrenciesUseCase(
    coroutineContextProvider: CoroutineContextProvider
) : BackgroundExecuteUseCase<String, List<CurrencyDomainModel>>(coroutineContextProvider)

class SearchCurrenciesUseCaseImpl(
    private val repository: CurrencyRepository,
    coroutineContextProvider: CoroutineContextProvider
) : SearchCurrenciesUseCase(coroutineContextProvider) {
    override suspend fun executeInBackground(
        request: String,
        coroutineScope: CoroutineScope
    ) = repository.search(request)
}
