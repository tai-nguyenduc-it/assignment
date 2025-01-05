package com.crypto.currency.presentation.screen.home.mapper

import com.crypto.currency.domain.usecase.model.CurrencyDomainModel
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel.Crypto
import com.crypto.currency.domain.usecase.model.CurrencyDomainModel.Fiat
import com.crypto.currency.presentation.screen.home.model.CurrencyPresentationModel

fun CurrencyDomainModel.toPresentation() = when (this) {
    is Crypto -> CurrencyPresentationModel.Crypto(
        id = id,
        name = name,
        symbol = symbol
    )

    is Fiat -> CurrencyPresentationModel.Fiat(
        id = id,
        name = name,
        symbol = symbol,
        code = code
    )
}