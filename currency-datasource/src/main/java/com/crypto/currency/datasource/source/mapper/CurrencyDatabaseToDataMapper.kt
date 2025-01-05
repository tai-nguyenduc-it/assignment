package com.crypto.currency.datasource.source.mapper

import com.crypto.currency.datasource.database.model.CurrencyDatabaseModel
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Crypto
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Fiat

fun CurrencyDatabaseModel.toData() =
    if (code.isEmpty()) {
        Crypto(
            id = id,
            name = name,
            symbol = symbol
        )
    } else {
        Fiat(
            id = id,
            name = name,
            symbol = symbol,
            code = code
        )
    }
