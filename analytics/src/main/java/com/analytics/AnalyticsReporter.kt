package com.analytics

interface AnalyticsReporter {
    fun logEvent(event: AnalyticsEvent)
}
