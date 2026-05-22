package com.storium.ui.screens.main.model

import com.storium.ui.theme.AppIcons
import org.jetbrains.compose.resources.DrawableResource
import org.jetbrains.compose.resources.StringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.mainNavProfile
import storium.shared.generated.resources.mainNavShop

enum class MainTab(
    val labelRes: StringResource,
    val activeIcon: DrawableResource,
    val inactiveIcon: DrawableResource,
) {
    Shop(
        labelRes = Res.string.mainNavShop,
        activeIcon = AppIcons.NavShopActive,
        inactiveIcon = AppIcons.NavShopInactive,
    ),
    Profile(
        labelRes = Res.string.mainNavProfile,
        activeIcon = AppIcons.NavProfileActive,
        inactiveIcon = AppIcons.NavProfileInactive,
    ),
}
