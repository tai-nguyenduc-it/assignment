package com.crypto.currency.presentation.screen.currency

import com.architecture.presentation.PresentationState
import com.crypto.currency.presentation.screen.home.model.CurrencyPresentationModel

data class CurrencyViewState(
    val currencies: List<CurrencyPresentationModel> = emptyList(),
    override val isLoading: Boolean = false,
    override val isError: Boolean = false
) : PresentationState
