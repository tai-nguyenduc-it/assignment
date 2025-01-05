package com.analytics

class DoNothingAnalyticsReporter : AnalyticsReporter {
    override fun logEvent(event: AnalyticsEvent) {
        println(event)
    }
}
