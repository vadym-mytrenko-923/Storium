package com.storium.data.remote.util

import com.storium.domain.system.logger.AppLogger
import io.ktor.client.plugins.logging.Logger

private const val NETWORK_TAG = "HTTP"

class HttpLogger(private val appLogger: AppLogger) : Logger {
    override fun log(message: String) {
        appLogger.log("$NETWORK_TAG: $message")
    }
}
