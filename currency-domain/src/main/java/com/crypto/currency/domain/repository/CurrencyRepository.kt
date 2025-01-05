package com.crypto.currency.domain.repository

import com.crypto.currency.domain.usecase.model.CurrencyDomainModel

interface CurrencyRepository {
    suspend fun addCurrency(currency: CurrencyDomainModel)
    suspend fun addCurrencies(currencies: List<CurrencyDomainModel>)
    suspend fun currencies(): List<CurrencyDomainModel>
    suspend fun deleteAllCurrencies()
}
