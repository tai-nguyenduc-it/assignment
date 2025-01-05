package com.crypto.currency.ui.screen.currency.mapper

import com.crypto.currency.presentation.screen.home.model.CurrencyPresentationModel
import com.crypto.currency.ui.screen.currency.model.CurrencyUiModel

fun CurrencyPresentationModel.toUi() = CurrencyUiModel(
    firstLetter = name.first().toString(),
    name = name,
    symbol = symbol
)
