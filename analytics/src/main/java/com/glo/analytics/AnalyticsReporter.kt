package com.glo.analytics

interface AnalyticsReporter {
    fun logEvent(event: AnalyticsEvent)
}
