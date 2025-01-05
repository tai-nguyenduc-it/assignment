package com.crypto.currency.datasource.source

import com.crypto.currency.datasource.database.CurrencyDao
import com.crypto.currency.datasource.source.mapper.toData
import com.crypto.currency.datasource.source.mapper.toDatabase
import com.crypto.currency.datasource.source.model.CurrencyDataModel

class CurrencySourceImpl(
    private val currencyDao: CurrencyDao
): CurrencySource {
    override suspend fun addCurrency(currency: CurrencyDataModel) {
        currencyDao.insertCurrency(currency.toDatabase())
    }

    override suspend fun addCurrencies(currencies: List<CurrencyDataModel>) {
        currencyDao.insertCurrencies(
            currencies.map { currency -> currency.toDatabase() }
        )
    }

    override suspend fun currencies() =
        currencyDao.getAllCurrencies().map { currency -> currency.toData() }

    override suspend fun deleteAllCurrencies() {
        currencyDao.deleteAllCurrencies()
    }
}
