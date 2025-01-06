package com.crypto.currency.presentation.screen.home

import com.architecture.presentation.BaseViewModel
import com.architecture.presentation.event.GenericEvent.Failed
import com.architecture.presentation.event.GenericEvent.Successful
import com.crypto.architecture.domain.UseCaseExecutorProvider
import com.crypto.currency.domain.usecase.AddCurrenciesUseCase
import com.crypto.currency.domain.usecase.DeleteCurrenciesUseCase
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel.Crypto
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel.Fiat
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val addCurrenciesUseCase: AddCurrenciesUseCase,
    private val deleteCurrenciesUseCase: DeleteCurrenciesUseCase,
    useCaseExecutorProvider: UseCaseExecutorProvider
) : BaseViewModel<HomeViewState>(
    useCaseExecutorProvider,
    HomeViewState()
) {
    fun onDeleteCurrenciesAction() {
        updateDeletingCurrenciesState(true)
        deleteCurrenciesUseCase.start(
            onResult = {
                sendEvent(Successful)
                updateDeletingCurrenciesState(false)
            },
            onException = {
                sendEvent(Failed)
                updateDeletingCurrenciesState(false)
            }
        )
    }

    fun onAddCurrenciesAction() {
        updateAddingCurrenciesState(true)
        addCurrenciesUseCase.start(
            value = currencies,
            onResult = {
                sendEvent(Successful)
                updateAddingCurrenciesState(false)
            },
            onException = {
                sendEvent(Failed)
                updateAddingCurrenciesState(false)
            }
        )
    }

    private fun updateAddingCurrenciesState(isAdding: Boolean) {
        updateState { lastState -> lastState.copy(isAddingCurrencies = isAdding) }
    }

    private fun updateDeletingCurrenciesState(isDeleting: Boolean) {
        updateState { lastState -> lastState.copy(isDeletingCurrencies = isDeleting) }
    }

    private val currencies = listOf(
        Crypto(id = "BTC", name = "Bitcoin", symbol = "BTC"),
        Crypto(id = "ETH", name = "Ethereum", symbol = "ETH"),
        Crypto(id = "XRP", name = "XRP", symbol = "XRP"),
        Crypto(id = "BCH", name = "Bitcoin Cash", symbol = "BCH"),
        Crypto(id = "LTC", name = "Litecoin", symbol = "LTC"),
        Crypto(id = "EOS", name = "EOS", symbol = "EOS"),
        Crypto(id = "BNB", name = "Binance Coin", symbol = "BNB"),
        Crypto(id = "LINK", name = "Chainlink", symbol = "LINK"),
        Crypto(id = "NEO", name = "NEO", symbol = "NEO"),
        Crypto(id = "ETC", name = "Ethereum Classic", symbol = "ETC"),
        Crypto(id = "ONT", name = "Ontology", symbol = "ONT"),
        Crypto(id = "CRO", name = "Crypto.com Chain", symbol = "CRO"),
        Crypto(id = "CUC", name = "Cucumber", symbol = "CUC"),
        Crypto(id = "USDC", name = "USD Coin", symbol = "USDC"),
        Fiat(id = "SGD", name = "Singapore Dollar", symbol = "$", code = "SGD"),
        Fiat(id = "EUR", name = "Euro", symbol = "€", code = "EUR"),
        Fiat(id = "GBP", name = "British Pound", symbol = "£", code = "GBP"),
        Fiat(id = "HKD", name = "Hong Kong Dollar", symbol = "$", code = "HKD"),
        Fiat(id = "JPY", name = "Japanese Yen", symbol = "¥", code = "JPY"),
        Fiat(id = "AUD", name = "Australian Dollar", symbol = "$", code = "AUD"),
        Fiat(id = "USD", name = "United States Dollar", symbol = "$", code = "USD")
    )
}