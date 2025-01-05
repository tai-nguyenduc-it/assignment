package com.crypto.currency.domain.repository

import com.crypto.currency.domain.usecase.model.CurrencyDomainModel

interface CurrencyRepository {
    suspend fun addCurrencies(currencies: List<CurrencyDomainModel>)
    suspend fun search(keyword: String): List<CurrencyDomainModel>
    suspend fun deleteAllCurrencies()
}
