package com.storium.util.date

/**
 * Platform-specific date formatting.
 * Implementations use platform-specific date APIs (SimpleDateFormat on Android, NSDateFormatter on iOS).
 */
fun interface DateFormatter {
    fun format(isoDate: String): String
}
