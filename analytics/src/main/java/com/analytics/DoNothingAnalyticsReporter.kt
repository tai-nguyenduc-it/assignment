package com.analytics

import com.common.logger.globalLogger

class DoNothingAnalyticsReporter : AnalyticsReporter {
    override fun logEvent(event: AnalyticsEvent) {
        globalLogger.i(event.name)
    }

    override fun logScreen(event: AnalyticsEvent) {
        globalLogger.i(event.name)
    }
}
