package com.analytics

interface AnalyticsReporter {
    fun logEvent(event: AnalyticsEvent)
    fun logScreen(event: AnalyticsEvent)
}
