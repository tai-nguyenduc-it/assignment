package com.crypto.currency.presentation.screen.home

import com.architecture.presentation.PresentationState

data class HomeViewState(
    val isAddingCurrencies: Boolean = false,
    val isDeletingCurrencies: Boolean = false,
    override val isLoading: Boolean = false,
    override val isError: Boolean = false
) : PresentationState
