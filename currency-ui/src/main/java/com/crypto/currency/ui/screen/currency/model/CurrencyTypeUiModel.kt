package com.crypto.currency.ui.screen.currency.model

import kotlinx.serialization.Serializable

@Serializable
sealed interface CurrencyTypeUiModel {
    @Serializable
    data object Crypto : CurrencyTypeUiModel

    @Serializable
    data object Fiat : CurrencyTypeUiModel

    @Serializable
    data object All : CurrencyTypeUiModel
}