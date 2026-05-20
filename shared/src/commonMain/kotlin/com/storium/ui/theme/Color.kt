package com.storium.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

object AppColorsLight {
    val Primary = Color(0xFF6C63FF)
    val PrimaryVariant = Color(0xFF5A52E0)
    val Secondary = Color(0xFFFF6B6B)
    val Background = Color(0xFFF8F8F8)
    val Surface = Color(0xFFFFFFFF)
    val Error = Color(0xFFE53935)
    val OnPrimary = Color(0xFFFFFFFF)
    val OnSecondary = Color(0xFFFFFFFF)
    val OnBackground = Color(0xFF1A1A2E)
    val OnSurface = Color(0xFF1A1A2E)
    val OnError = Color(0xFFFFFFFF)
    val TextPrimary = Color(0xFF1A1A2E)
    val TextSecondary = Color(0xFF6E6E82)
    val TextTertiary = Color(0xFF9E9EB8)
    val TextDisabled = Color(0xFFBDBDD4)
    val BorderDefault = Color(0xFFE8E8F0)
    val BorderFocused = Color(0xFF6C63FF)
    val CardBackground = Color(0xFFFFFFFF)
    val CardShadow = Color(0x0D000000)
    val SearchBarBackground = Color(0xFFF0F0F5)
    val RatingStarActive = Color(0xFFFFC107)
    val RatingStarInactive = Color(0xFFE0E0E0)
    val PriceText = Color(0xFF6C63FF)
    val DiscountBadge = Color(0xFFFF6B6B)
    val CategoryChipBackground = Color(0xFFF0EEFF)
    val CategoryChipText = Color(0xFF6C63FF)
}

object AppColorsDark {
    val Primary = Color(0xFF8B83FF)
    val PrimaryVariant = Color(0xFF6C63FF)
    val Secondary = Color(0xFFFF8A8A)
    val Background = Color(0xFF121212)
    val Surface = Color(0xFF1E1E2E)
    val Error = Color(0xFFEF5350)
    val OnPrimary = Color(0xFFFFFFFF)
    val OnSecondary = Color(0xFFFFFFFF)
    val OnBackground = Color(0xFFE8E8F0)
    val OnSurface = Color(0xFFE8E8F0)
    val OnError = Color(0xFFFFFFFF)
    val TextPrimary = Color(0xFFE8E8F0)
    val TextSecondary = Color(0xFF9E9EB8)
    val TextTertiary = Color(0xFF6E6E82)
    val TextDisabled = Color(0xFF4A4A5E)
    val BorderDefault = Color(0xFF2E2E42)
    val BorderFocused = Color(0xFF8B83FF)
    val CardBackground = Color(0xFF1E1E2E)
    val CardShadow = Color(0x1AFFFFFF)
    val SearchBarBackground = Color(0xFF2A2A3E)
    val RatingStarActive = Color(0xFFFFC107)
    val RatingStarInactive = Color(0xFF4A4A5E)
    val PriceText = Color(0xFF8B83FF)
    val DiscountBadge = Color(0xFFFF8A8A)
    val CategoryChipBackground = Color(0xFF2A2A42)
    val CategoryChipText = Color(0xFF8B83FF)
}

@Immutable
data class AppColorsScheme(
    val primary: Color = Color.Unspecified,
    val primaryVariant: Color = Color.Unspecified,
    val secondary: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val error: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val onSecondary: Color = Color.Unspecified,
    val onBackground: Color = Color.Unspecified,
    val onSurface: Color = Color.Unspecified,
    val onError: Color = Color.Unspecified,
    val textPrimary: Color = Color.Unspecified,
    val textSecondary: Color = Color.Unspecified,
    val textTertiary: Color = Color.Unspecified,
    val textDisabled: Color = Color.Unspecified,
    val borderDefault: Color = Color.Unspecified,
    val borderFocused: Color = Color.Unspecified,
    val cardBackground: Color = Color.Unspecified,
    val cardShadow: Color = Color.Unspecified,
    val searchBarBackground: Color = Color.Unspecified,
    val ratingStarActive: Color = Color.Unspecified,
    val ratingStarInactive: Color = Color.Unspecified,
    val priceText: Color = Color.Unspecified,
    val discountBadge: Color = Color.Unspecified,
    val categoryChipBackground: Color = Color.Unspecified,
    val categoryChipText: Color = Color.Unspecified,
)

val LightAppColorsScheme = AppColorsScheme(
    primary = AppColorsLight.Primary,
    primaryVariant = AppColorsLight.PrimaryVariant,
    secondary = AppColorsLight.Secondary,
    background = AppColorsLight.Background,
    surface = AppColorsLight.Surface,
    error = AppColorsLight.Error,
    onPrimary = AppColorsLight.OnPrimary,
    onSecondary = AppColorsLight.OnSecondary,
    onBackground = AppColorsLight.OnBackground,
    onSurface = AppColorsLight.OnSurface,
    onError = AppColorsLight.OnError,
    textPrimary = AppColorsLight.TextPrimary,
    textSecondary = AppColorsLight.TextSecondary,
    textTertiary = AppColorsLight.TextTertiary,
    textDisabled = AppColorsLight.TextDisabled,
    borderDefault = AppColorsLight.BorderDefault,
    borderFocused = AppColorsLight.BorderFocused,
    cardBackground = AppColorsLight.CardBackground,
    cardShadow = AppColorsLight.CardShadow,
    searchBarBackground = AppColorsLight.SearchBarBackground,
    ratingStarActive = AppColorsLight.RatingStarActive,
    ratingStarInactive = AppColorsLight.RatingStarInactive,
    priceText = AppColorsLight.PriceText,
    discountBadge = AppColorsLight.DiscountBadge,
    categoryChipBackground = AppColorsLight.CategoryChipBackground,
    categoryChipText = AppColorsLight.CategoryChipText,
)

val DarkAppColorsScheme = AppColorsScheme(
    primary = AppColorsDark.Primary,
    primaryVariant = AppColorsDark.PrimaryVariant,
    secondary = AppColorsDark.Secondary,
    background = AppColorsDark.Background,
    surface = AppColorsDark.Surface,
    error = AppColorsDark.Error,
    onPrimary = AppColorsDark.OnPrimary,
    onSecondary = AppColorsDark.OnSecondary,
    onBackground = AppColorsDark.OnBackground,
    onSurface = AppColorsDark.OnSurface,
    onError = AppColorsDark.OnError,
    textPrimary = AppColorsDark.TextPrimary,
    textSecondary = AppColorsDark.TextSecondary,
    textTertiary = AppColorsDark.TextTertiary,
    textDisabled = AppColorsDark.TextDisabled,
    borderDefault = AppColorsDark.BorderDefault,
    borderFocused = AppColorsDark.BorderFocused,
    cardBackground = AppColorsDark.CardBackground,
    cardShadow = AppColorsDark.CardShadow,
    searchBarBackground = AppColorsDark.SearchBarBackground,
    ratingStarActive = AppColorsDark.RatingStarActive,
    ratingStarInactive = AppColorsDark.RatingStarInactive,
    priceText = AppColorsDark.PriceText,
    discountBadge = AppColorsDark.DiscountBadge,
    categoryChipBackground = AppColorsDark.CategoryChipBackground,
    categoryChipText = AppColorsDark.CategoryChipText,
)
