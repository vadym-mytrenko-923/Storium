package com.storium.ui.screens.settings.model

import com.storium.ui.theme.AppIcons
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.settingsThemeDark
import storium.shared.generated.resources.settingsThemeLight

enum class ThemeUiModel(
    val labelRes: StringResource,
    val iconRes: DrawableResource,
) {
    Light(
        labelRes = Res.string.settingsThemeLight,
        iconRes = AppIcons.ThemeLight,
    ),
    Dark(
        labelRes = Res.string.settingsThemeDark,
        iconRes = AppIcons.ThemeDark,
    ),
}
