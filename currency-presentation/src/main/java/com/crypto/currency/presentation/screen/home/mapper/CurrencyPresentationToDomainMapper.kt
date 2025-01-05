package com.crypto.currency.presentation.screen.home.mapper

import com.crypto.currency.domain.usecase.model.CurrencyDomainModel
import com.crypto.currency.presentation.screen.home.model.CurrencyPresentationModel
import com.crypto.currency.presentation.screen.home.model.CurrencyPresentationModel.Crypto
import com.crypto.currency.presentation.screen.home.model.CurrencyPresentationModel.Fiat

fun CurrencyPresentationModel.toDomain() = when (this) {
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