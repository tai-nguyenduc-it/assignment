package com.crypto.currency.ui.navigation.route

import kotlinx.serialization.Serializable

sealed class Route {
    @Serializable
    data object Splash : Route()

    @Serializable
    data object Home : Route()
}
