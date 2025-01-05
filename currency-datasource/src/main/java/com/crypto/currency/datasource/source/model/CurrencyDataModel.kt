package com.crypto.currency.datasource.source.model

sealed class CurrencyDataModel(
    open val id: String,
    open val name: String,
    open val symbol: String
) {
    data class Crypto(
        override val id: String,
        override val name: String,
        override val symbol: String
    ) : CurrencyDataModel(id, name, symbol)

    data class Fiat(
        override val id: String,
        override val name: String,
        override val symbol: String,
        val code: String
    ) : CurrencyDataModel(id, name, symbol)
}