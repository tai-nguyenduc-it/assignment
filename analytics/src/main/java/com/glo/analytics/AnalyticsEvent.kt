package com.glo.analytics

typealias EventName = String

interface AnalyticsEvent {
    val name: EventName
    fun parameters(): EventParameters = EventParameters()
}
