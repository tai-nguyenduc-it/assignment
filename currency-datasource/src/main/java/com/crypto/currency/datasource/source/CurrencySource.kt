package com.crypto.currency.datasource.source

import com.crypto.currency.datasource.source.model.CurrencyDataModel

interface CurrencySource {
    suspend fun addCurrencies(currencies: List<CurrencyDataModel>)
    suspend fun search(keyword: String): List<CurrencyDataModel>
    suspend fun deleteAllCurrencies()
}
