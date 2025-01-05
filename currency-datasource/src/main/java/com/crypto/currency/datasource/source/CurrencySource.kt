package com.crypto.currency.datasource.source

import com.crypto.currency.datasource.source.model.CurrencyDataModel

interface CurrencySource {
    suspend fun addCurrency(currency: CurrencyDataModel)
    suspend fun addCurrencies(currencies: List<CurrencyDataModel>)
    suspend fun currencies(): List<CurrencyDataModel>
    suspend fun deleteAllCurrencies()
}
