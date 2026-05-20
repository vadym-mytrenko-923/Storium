package com.storium.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.staticCompositionLocalOf

private val LightColorScheme = lightColorScheme(
    primary = AppColorsLight.Primary,
    background = AppColorsLight.Background,
    surface = AppColorsLight.Surface,
    error = AppColorsLight.Error,
    onPrimary = AppColorsLight.OnPrimary,
    onBackground = AppColorsLight.TextPrimary,
    onSurface = AppColorsLight.TextPrimary,
    outline = AppColorsLight.BorderDefault,
)

private val DarkColorScheme = darkColorScheme(
    primary = AppColorsDark.Primary,
    background = AppColorsDark.Background,
    surface = AppColorsDark.Surface,
    error = AppColorsDark.Error,
    onPrimary = AppColorsDark.OnPrimary,
    onBackground = AppColorsDark.TextPrimary,
    onSurface = AppColorsDark.TextPrimary,
    outline = AppColorsDark.BorderDefault,
)

private val LocalAppColorsScheme = staticCompositionLocalOf { AppColorsScheme() }

val MaterialTheme.appColors: AppColorsScheme
    @Composable
    @ReadOnlyComposable
    get() = LocalAppColorsScheme.current

@Composable
fun StoriumTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val appColorsScheme = if (darkTheme) DarkAppColorsScheme else LightAppColorsScheme

    CompositionLocalProvider(LocalAppColorsScheme provides appColorsScheme) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = AppTypography,
            content = content,
        )
    }
}
