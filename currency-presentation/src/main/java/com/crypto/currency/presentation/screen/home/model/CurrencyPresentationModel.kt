package com.crypto.currency.presentation.screen.home.model

sealed class CurrencyPresentationModel(
    open val id: String,
    open val name: String,
    open val symbol: String
) {
    data class Crypto(
        override val id: String,
        override val name: String,
        override val symbol: String
    ) : CurrencyPresentationModel(id, name, symbol)

    data class Fiat(
        override val id: String,
        override val name: String,
        override val symbol: String,
        val code: String
    ) : CurrencyPresentationModel(id, name, symbol)
}
