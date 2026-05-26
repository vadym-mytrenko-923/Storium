package com.storium.util.date

import platform.Foundation.NSDateFormatter
import platform.Foundation.NSLocale
import platform.Foundation.NSTimeZone
import platform.Foundation.currentLocale
import platform.Foundation.localeWithLocaleIdentifier
import platform.Foundation.timeZoneWithName

private const val ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private const val UTC = "UTC"
private const val POSIX_LOCALE = "en_US_POSIX"

actual fun formatIsoDate(isoDate: String, outputPattern: String): String {
    val isoFormatter = NSDateFormatter().apply {
        dateFormat = ISO_8601_PATTERN
        locale = NSLocale.localeWithLocaleIdentifier(POSIX_LOCALE)
        timeZone = NSTimeZone.timeZoneWithName(UTC) ?: return isoDate
    }

    val outputFormatter = NSDateFormatter().apply {
        dateFormat = outputPattern
        locale = NSLocale.currentLocale
    }

    val date = isoFormatter.dateFromString(isoDate) ?: return isoDate
    return outputFormatter.stringFromDate(date)
}
