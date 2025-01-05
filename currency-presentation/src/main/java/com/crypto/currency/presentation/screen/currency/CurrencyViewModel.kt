package com.crypto.currency.presentation.screen.currency

import com.architecture.presentation.BaseViewModel
import com.crypto.architecture.domain.UseCaseExecutorProvider
import com.crypto.architecture.domain.usecase.Cancellable
import com.crypto.currency.domain.usecase.SearchCurrenciesUseCase
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel
import com.crypto.currency.presentation.screen.home.mapper.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(
    private val searchCurrenciesUseCase: SearchCurrenciesUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<CurrencyViewState>(
    useCaseExecutorProvider,
    CurrencyViewState()
) {

    private var cancellable: Cancellable? = null

    fun onSearchAction(keyword: String) {
        cancellable?.cancel()
        updateLoadingState(true)
        cancellable = searchCurrenciesUseCase.start(
            value = keyword,
            onResult = ::updateCurrencies
        )
    }

    private fun updateCurrencies(currencies: List<CurrencyDomainModel>) {
        updateState { lastState ->
            lastState.copy(
                currencies = currencies.map { currency -> currency.toPresentation() },
                isLoading = false
            )
        }
    }

    private fun updateLoadingState(isLoading: Boolean) {
        updateState { lastState -> lastState.copy(isLoading = isLoading) }
    }

    override fun onClear() {
        cancellable?.cancel()
        cancellable = null
    }
}