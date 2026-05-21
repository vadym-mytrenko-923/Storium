package com.storium.ui.theme

import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.Color

object AppColorsLight {
    val Primary = Color(0xFFDB3022)
    val Error = Color(0xFFF01F0E)
    val Background = Color(0xFFF9F9F9)
    val Surface = Color(0xFFFFFFFF)
    val OnPrimary = Color(0xFFFFFFFF)
    val TextPrimary = Color(0xFF222222)
    val TextInput = Color(0xFF2D2D2D)
    val TextSecondary = Color(0xFF9B9B9B)
    val TextDisabled = Color(0xFFCCCCCC)
    val BorderDefault = Color(0xFFE0E0E0)
    val BorderFocused = Color(0xFFDB3022)
    val InputBackground = Color(0xFFFFFFFF)
    val CardBackground = Color(0xFFFFFFFF)
    val CardShadow = Color(0x14000000)
    val RatingStarActive = Color(0xFFFFBA49)
    val RatingStarInactive = Color(0xFFE0E0E0)
    val CategoryChipBackground = Color(0xFF222222)
    val CategoryChipText = Color(0xFFFFFFFF)
    val BtnPrimary = Color(0xFFDB3022)
    val BtnPrimaryPressed = Color(0xFFC62828)
    val BtnPrimaryDisabled = Color(0xFFCCCCCC)
    val BtnPrimaryText = Color(0xFFFFFFFF)
    val BtnPrimaryTextDisabled = Color(0xFFFFFFFF)
    val BtnPrimaryShadow = Color(0x40DB3022)
    val BottomNavActive = Color(0xFFDB3022)
    val BottomNavInactive = Color(0xFF9B9B9B)
    val Divider = Color(0xFFE0E0E0)
    val TextPrimaryOnDark = Color(0xFFFFFFFF)
    val IconOnDark = Color(0xFFFFFFFF)
    val BackgroundSuccess = Color(0xFF61D782)
    val FullscreenLoaderBackground = Color(0x80000000)
}

object AppColorsDark {
    val Primary = Color(0xFFEF5350)
    val Error = Color(0xFFF44336)
    val Background = Color(0xFF121212)
    val Surface = Color(0xFF1E1E1E)
    val OnPrimary = Color(0xFFFFFFFF)
    val TextPrimary = Color(0xFFE8E8E8)
    val TextInput = Color(0xFFE0E0E0)
    val TextSecondary = Color(0xFF9B9B9B)
    val TextDisabled = Color(0xFF555555)
    val BorderDefault = Color(0xFF333333)
    val BorderFocused = Color(0xFFEF5350)
    val InputBackground = Color(0xFF2A2A2A)
    val CardBackground = Color(0xFF1E1E1E)
    val CardShadow = Color(0x14FFFFFF)
    val RatingStarActive = Color(0xFFFFBA49)
    val RatingStarInactive = Color(0xFF555555)
    val CategoryChipBackground = Color(0xFFE8E8E8)
    val CategoryChipText = Color(0xFF121212)
    val BtnPrimary = Color(0xFFEF5350)
    val BtnPrimaryPressed = Color(0xFFD32F2F)
    val BtnPrimaryDisabled = Color(0xFF555555)
    val BtnPrimaryText = Color(0xFFFFFFFF)
    val BtnPrimaryTextDisabled = Color(0xFF9B9B9B)
    val BtnPrimaryShadow = Color(0x40EF5350)
    val BottomNavActive = Color(0xFFEF5350)
    val BottomNavInactive = Color(0xFF9B9B9B)
    val Divider = Color(0xFF333333)
    val TextPrimaryOnDark = Color(0xFFFFFFFF)
    val IconOnDark = Color(0xFFFFFFFF)
    val BackgroundSuccess = Color(0xFF4CAF50)
    val FullscreenLoaderBackground = Color(0x80000000)
}

@Immutable
data class AppColorsScheme(
    val primary: Color = Color.Unspecified,
    val error: Color = Color.Unspecified,
    val background: Color = Color.Unspecified,
    val surface: Color = Color.Unspecified,
    val onPrimary: Color = Color.Unspecified,
    val textPrimary: Color = Color.Unspecified,
    val textInput: Color = Color.Unspecified,
    val textSecondary: Color = Color.Unspecified,
    val textDisabled: Color = Color.Unspecified,
    val borderDefault: Color = Color.Unspecified,
    val borderFocused: Color = Color.Unspecified,
    val inputBackground: Color = Color.Unspecified,
    val cardBackground: Color = Color.Unspecified,
    val cardShadow: Color = Color.Unspecified,
    val ratingStarActive: Color = Color.Unspecified,
    val ratingStarInactive: Color = Color.Unspecified,
    val btnPrimary: Color = Color.Unspecified,
    val btnPrimaryPressed: Color = Color.Unspecified,
    val btnPrimaryDisabled: Color = Color.Unspecified,
    val btnPrimaryText: Color = Color.Unspecified,
    val btnPrimaryTextDisabled: Color = Color.Unspecified,
    val btnPrimaryShadow: Color = Color.Unspecified,
    val categoryChipBackground: Color = Color.Unspecified,
    val categoryChipText: Color = Color.Unspecified,
    val bottomNavActive: Color = Color.Unspecified,
    val bottomNavInactive: Color = Color.Unspecified,
    val divider: Color = Color.Unspecified,
    val textPrimaryOnDark: Color = Color.Unspecified,
    val iconOnDark: Color = Color.Unspecified,
    val backgroundSuccess: Color = Color.Unspecified,
    val fullscreenLoaderBackground: Color = Color.Unspecified,
)

val LightAppColorsScheme = AppColorsScheme(
    primary = AppColorsLight.Primary,
    error = AppColorsLight.Error,
    background = AppColorsLight.Background,
    surface = AppColorsLight.Surface,
    onPrimary = AppColorsLight.OnPrimary,
    textPrimary = AppColorsLight.TextPrimary,
    textInput = AppColorsLight.TextInput,
    textSecondary = AppColorsLight.TextSecondary,
    textDisabled = AppColorsLight.TextDisabled,
    borderDefault = AppColorsLight.BorderDefault,
    borderFocused = AppColorsLight.BorderFocused,
    inputBackground = AppColorsLight.InputBackground,
    cardBackground = AppColorsLight.CardBackground,
    cardShadow = AppColorsLight.CardShadow,
    ratingStarActive = AppColorsLight.RatingStarActive,
    ratingStarInactive = AppColorsLight.RatingStarInactive,
    btnPrimary = AppColorsLight.BtnPrimary,
    btnPrimaryPressed = AppColorsLight.BtnPrimaryPressed,
    btnPrimaryDisabled = AppColorsLight.BtnPrimaryDisabled,
    btnPrimaryText = AppColorsLight.BtnPrimaryText,
    btnPrimaryTextDisabled = AppColorsLight.BtnPrimaryTextDisabled,
    btnPrimaryShadow = AppColorsLight.BtnPrimaryShadow,
    categoryChipBackground = AppColorsLight.CategoryChipBackground,
    categoryChipText = AppColorsLight.CategoryChipText,
    bottomNavActive = AppColorsLight.BottomNavActive,
    bottomNavInactive = AppColorsLight.BottomNavInactive,
    divider = AppColorsLight.Divider,
    textPrimaryOnDark = AppColorsLight.TextPrimaryOnDark,
    iconOnDark = AppColorsLight.IconOnDark,
    backgroundSuccess = AppColorsLight.BackgroundSuccess,
    fullscreenLoaderBackground = AppColorsLight.FullscreenLoaderBackground,
)

val DarkAppColorsScheme = AppColorsScheme(
    primary = AppColorsDark.Primary,
    error = AppColorsDark.Error,
    background = AppColorsDark.Background,
    surface = AppColorsDark.Surface,
    onPrimary = AppColorsDark.OnPrimary,
    textPrimary = AppColorsDark.TextPrimary,
    textInput = AppColorsDark.TextInput,
    textSecondary = AppColorsDark.TextSecondary,
    textDisabled = AppColorsDark.TextDisabled,
    borderDefault = AppColorsDark.BorderDefault,
    borderFocused = AppColorsDark.BorderFocused,
    inputBackground = AppColorsDark.InputBackground,
    cardBackground = AppColorsDark.CardBackground,
    cardShadow = AppColorsDark.CardShadow,
    ratingStarActive = AppColorsDark.RatingStarActive,
    ratingStarInactive = AppColorsDark.RatingStarInactive,
    btnPrimary = AppColorsDark.BtnPrimary,
    btnPrimaryPressed = AppColorsDark.BtnPrimaryPressed,
    btnPrimaryDisabled = AppColorsDark.BtnPrimaryDisabled,
    btnPrimaryText = AppColorsDark.BtnPrimaryText,
    btnPrimaryTextDisabled = AppColorsDark.BtnPrimaryTextDisabled,
    btnPrimaryShadow = AppColorsDark.BtnPrimaryShadow,
    categoryChipBackground = AppColorsDark.CategoryChipBackground,
    categoryChipText = AppColorsDark.CategoryChipText,
    bottomNavActive = AppColorsDark.BottomNavActive,
    bottomNavInactive = AppColorsDark.BottomNavInactive,
    divider = AppColorsDark.Divider,
    textPrimaryOnDark = AppColorsDark.TextPrimaryOnDark,
    iconOnDark = AppColorsDark.IconOnDark,
    backgroundSuccess = AppColorsDark.BackgroundSuccess,
    fullscreenLoaderBackground = AppColorsDark.FullscreenLoaderBackground,
)
