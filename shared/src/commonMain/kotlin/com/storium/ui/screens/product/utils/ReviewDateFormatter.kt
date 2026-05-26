package com.storium.ui.screens.product.utils

import com.storium.util.date.DateFormatter
import com.storium.util.date.formatIsoDate

private const val REVIEW_DATE_PATTERN = "MMM d, yyyy"

class ReviewDateFormatter : DateFormatter {
    override fun format(isoDate: String): String = formatIsoDate(isoDate, REVIEW_DATE_PATTERN)
}
