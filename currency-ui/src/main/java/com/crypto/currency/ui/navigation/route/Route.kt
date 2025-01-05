package com.crypto.currency.ui.navigation.route

import com.architecture.ui.navigation.serializableType
import com.crypto.currency.ui.screen.currency.model.CurrencyTypeUiModel
import kotlinx.serialization.Serializable
import kotlin.reflect.typeOf

sealed class Route {
    @Serializable
    data object Splash : Route()

    @Serializable
    data object Home : Route()

    @Serializable
    data class Currency(val currencyType: CurrencyTypeUiModel) : Route() {
        companion object {
            val navTypeMap =
                mapOf(typeOf<CurrencyTypeUiModel>() to serializableType<CurrencyTypeUiModel>())
        }
    }
}
