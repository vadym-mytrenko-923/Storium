package com.storium.domain.system.logger

interface AppLogger : Logger {
    fun logException(throwable: Throwable)
    fun logClick(buttonName: String)
}
