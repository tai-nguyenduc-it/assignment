package com.analytics.event

import com.analytics.AnalyticsEvent
import com.analytics.EventName

sealed class ScreenEvent(override val name: EventName) : AnalyticsEvent {
    data object Splash : ScreenEvent("Splash Screen")
    data object Home : ScreenEvent("Home Screen")
    data class Currency(private val type: String) : ScreenEvent("$type - Currency")
}
