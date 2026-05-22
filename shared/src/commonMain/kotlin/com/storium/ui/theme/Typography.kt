package com.storium.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import org.jetbrains.compose.resources.Font
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.metropolis_bold
import storium.shared.generated.resources.metropolis_medium
import storium.shared.generated.resources.metropolis_regular
import storium.shared.generated.resources.metropolis_semibold

val MetropolisFontFamily
    @Composable get() = FontFamily(
        Font(Res.font.metropolis_regular, FontWeight.Normal),
        Font(Res.font.metropolis_medium, FontWeight.Medium),
        Font(Res.font.metropolis_semibold, FontWeight.SemiBold),
        Font(Res.font.metropolis_bold, FontWeight.Bold),
    )

val AppTypography
    @Composable get() = Typography(
        headlineLarge = TextStyle(
            fontFamily = MetropolisFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = textSizeLargest,
            lineHeight = textLineHeightLargest,
        ),
        headlineMedium = TextStyle(
            fontFamily = MetropolisFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = textSizeLarge,
            lineHeight = textLineHeightLarge,
        ),
        titleLarge = TextStyle(
            fontFamily = MetropolisFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = textSizeMediumLarge,
            lineHeight = textLineHeightToolbar,
        ),
        titleMedium = TextStyle(
            fontFamily = MetropolisFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = textSizeMedium,
            lineHeight = textLineHeightMedium,
        ),
        bodyLarge = TextStyle(
            fontFamily = MetropolisFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = textSizeNormal,
            lineHeight = textLineHeightNormalExpanded,
            letterSpacing = (-0.15).sp,
        ),
        bodyMedium = TextStyle(
            fontFamily = MetropolisFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = textSizeNormal,
            lineHeight = textLineHeightNormal,
        ),
        bodySmall = TextStyle(
            fontFamily = MetropolisFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = textSizeSmall,
            lineHeight = textLineHeightSmall,
        ),
        labelLarge = TextStyle(
            fontFamily = MetropolisFontFamily,
            fontWeight = FontWeight.Medium,
            fontSize = textSizeNormal,
            lineHeight = textLineHeightNormal,
        ),
        labelSmall = TextStyle(
            fontFamily = MetropolisFontFamily,
            fontWeight = FontWeight.Normal,
            fontSize = textSizeSmall,
            lineHeight = textLineHeightSmall,
            letterSpacing = (-0.17).sp,
        ),
    )
