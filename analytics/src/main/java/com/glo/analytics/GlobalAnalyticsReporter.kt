package com.glo.analytics

val globalAnalyticsReporter: GlobalAnalyticsReporter by lazy { _globalAnalyticsReporter }
var _globalAnalyticsReporter = GlobalAnalyticsReporter()

class GlobalAnalyticsReporter(
    private val analyticsReporter: AnalyticsReporter = DoNothingAnalyticsReporter()
) : AnalyticsReporter {

    override fun logEvent(event: AnalyticsEvent) {
        analyticsReporter.logEvent(event)
    }
}
