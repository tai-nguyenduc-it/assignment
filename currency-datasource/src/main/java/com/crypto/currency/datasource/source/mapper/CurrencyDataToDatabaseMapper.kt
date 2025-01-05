package com.crypto.currency.datasource.source.mapper

import com.crypto.currency.datasource.database.model.CurrencyDatabaseModel
import com.crypto.currency.datasource.source.model.CurrencyDataModel
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Crypto
import com.crypto.currency.datasource.source.model.CurrencyDataModel.Fiat

fun CurrencyDataModel.toDatabase() = when (this) {
    is Crypto -> CurrencyDatabaseModel(
        id = id,
        name = name,
        symbol = symbol,
        code = ""
    )

    is Fiat -> CurrencyDatabaseModel(
        id = id,
        name = name,
        symbol = symbol,
        code = code
    )
}
