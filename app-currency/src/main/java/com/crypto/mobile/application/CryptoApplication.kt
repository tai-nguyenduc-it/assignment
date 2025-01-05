package com.crypto.mobile.application

import android.app.Application
import com.common.logger.GlobalLogger
import com.common.logger.Logger
import com.common.logger._globalLogger
import com.glo.analytics.GlobalAnalyticsReporter
import com.glo.analytics._globalAnalyticsReporter
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject

@HiltAndroidApp
class CryptoApplication : Application() {
    @Inject
    lateinit var logger: Logger

    override fun onCreate() {
        super.onCreate()
        _globalLogger = GlobalLogger(logger)
        _globalAnalyticsReporter = GlobalAnalyticsReporter()
    }
}
