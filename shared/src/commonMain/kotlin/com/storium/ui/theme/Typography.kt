package com.storium.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

val AppTypography = Typography(
    headlineLarge = TextStyle(
        fontWeight = FontWeight.Bold,
        fontSize = textSizeLargest,
        lineHeight = textLineHeightLargest,
    ),
    headlineMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = textSizeLarge,
        lineHeight = textLineHeightLarge,
    ),
    titleMedium = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = textSizeMedium,
        lineHeight = textLineHeightMedium,
    ),
    bodyLarge = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = textSizeNormal,
        lineHeight = textLineHeightNormalExpanded,
        letterSpacing = (-0.15).sp,
    ),
    bodyMedium = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = textSizeNormal,
        lineHeight = textLineHeightNormal,
    ),
    bodySmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = textSizeSmall,
        lineHeight = textLineHeightSmall,
    ),
    labelLarge = TextStyle(
        fontWeight = FontWeight.Medium,
        fontSize = textSizeNormal,
        lineHeight = textLineHeightNormal,
    ),
    labelSmall = TextStyle(
        fontWeight = FontWeight.Normal,
        fontSize = textSizeSmall,
        lineHeight = textLineHeightSmall,
        letterSpacing = (-0.17).sp,
    ),
)
