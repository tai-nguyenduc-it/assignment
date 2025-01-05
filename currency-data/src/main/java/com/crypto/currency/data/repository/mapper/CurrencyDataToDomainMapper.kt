package com.crypto.currency.data.repository.mapper

import com.crypto.currency.datasource.source.model.CurrencyDataModel
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Crypto
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Fiat
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel

fun CurrencyDataModel.toDomain() = when (this) {
    is Crypto -> CurrencyDomainModel.Crypto(
        id = id,
        name = name,
        symbol = symbol
    )

    is Fiat -> CurrencyDomainModel.Fiat(
        id = id,
        name = name,
        symbol = symbol,
        code = code
    )
}