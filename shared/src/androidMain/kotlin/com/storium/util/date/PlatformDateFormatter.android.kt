package com.storium.util.date

import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

private const val ISO_8601_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
private const val UTC = "UTC"

actual fun formatIsoDate(isoDate: String, outputPattern: String): String {
    val outputFormat = SimpleDateFormat(outputPattern, Locale.getDefault())
    val isoFormat = SimpleDateFormat(ISO_8601_PATTERN, Locale.US).apply {
        timeZone = TimeZone.getTimeZone(UTC)
    }

    val date = isoFormat.parse(isoDate) ?: return isoDate
    return outputFormat.format(date)
}
