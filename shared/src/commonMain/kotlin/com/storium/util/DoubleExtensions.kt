package com.storium.util

import kotlin.math.pow
import kotlin.math.roundToInt

private const val DECIMAL_BASE = 10.0
private const val MAX_PERCENT = 100.0

/**
 * Rounds a Double to the specified number of decimal places.
 * E.g. 14.993 with 2 places → 14.99, 4.267 with 1 place → 4.3
 */
fun Double.roundTo(decimalPlaces: Int): Double {
    val multiplier = DECIMAL_BASE.pow(decimalPlaces)
    return (this * multiplier).roundToInt() / multiplier
}

/**
 * Reduces the value by a given percentage.
 * E.g. 100.0.reduceByPercent(15.0) → 85.0
 */
fun Double.reduceByPercent(percent: Double): Double = this * (1 - percent / MAX_PERCENT)
