package com.analytics.event

import com.analytics.AnalyticsEvent
import com.analytics.EventName

sealed class HomeEvent(override val name: EventName) : AnalyticsEvent {
    data object CleanData : HomeEvent("Clean Data Clicked")
    data object InsertData : HomeEvent("Insert Data Clicked")
}
