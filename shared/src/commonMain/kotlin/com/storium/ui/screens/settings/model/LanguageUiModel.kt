package com.storium.ui.screens.settings.model

import com.storium.ui.theme.AppIcons
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.settingsLanguageEnglish
import storium.shared.generated.resources.settingsLanguageSpanish

enum class LanguageUiModel(
    val labelRes: StringResource,
    val iconRes: DrawableResource,
) {
    English(
        labelRes = Res.string.settingsLanguageEnglish,
        iconRes = AppIcons.FlagEnglish,
    ),
    Spanish(
        labelRes = Res.string.settingsLanguageSpanish,
        iconRes = AppIcons.FlagSpanish,
    ),
}
