package com.storium.util.date

/**
 * Platform-specific ISO-8601 date string formatting.
 */
expect fun formatIsoDate(isoDate: String, outputPattern: String): String
