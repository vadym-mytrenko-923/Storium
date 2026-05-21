package com.storium.data.system.logger

import co.touchlab.kermit.Logger
import com.storium.domain.system.logger.AppLogger

private const val TAG = "AppLogger"

class AppLoggerImpl : AppLogger {

    override fun log(message: String) {
        Logger.d(TAG) { message }
    }

    override fun logException(throwable: Throwable) {
        Logger.e(TAG, throwable) { throwable.message ?: throwable.toString() }
    }

    override fun logClick(buttonName: String) {
        Logger.d(TAG) { "Clicked \"$buttonName\"" }
    }
}
