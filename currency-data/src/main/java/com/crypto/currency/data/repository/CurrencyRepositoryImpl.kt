package com.crypto.currency.data.repository

import com.architecture.data.BaseRepository
import com.crypto.currency.data.repository.mapper.toData
import com.crypto.currency.data.repository.mapper.toDomain
import com.crypto.currency.datasource.source.CurrencySource
import com.crypto.currency.domain.repository.CurrencyRepository
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel

class CurrencyRepositoryImpl(
    private val currencySource: CurrencySource
) : CurrencyRepository, BaseRepository() {
    override suspend fun addCurrencies(currencies: List<CurrencyDomainModel>) {
        currencySource.addCurrencies(
            currencies.map { currency -> currency.toData() }
        )
    }

    override suspend fun search(keyword: String) = executeSafely {
        currencySource.search(keyword).map { currency -> currency.toDomain() }
    }

    override suspend fun deleteAllCurrencies() {
        currencySource.deleteAllCurrencies()
    }
}