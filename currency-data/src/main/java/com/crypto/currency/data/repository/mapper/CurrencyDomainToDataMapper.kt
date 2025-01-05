package com.crypto.currency.data.repository.mapper

import com.crypto.currency.datasource.source.model.CurrencyDataModel
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel.Crypto
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel.Fiat

fun CurrencyDomainModel.toData() = when (this) {
    is Crypto -> CurrencyDataModel.Crypto(
        id = id,
        name = name,
        symbol = symbol
    )

    is Fiat -> CurrencyDataModel.Fiat(
        id = id,
        name = name,
        symbol = symbol,
        code = code
    )
}