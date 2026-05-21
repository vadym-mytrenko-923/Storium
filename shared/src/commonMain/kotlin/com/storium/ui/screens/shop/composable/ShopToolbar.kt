package com.storium.ui.screens.shop.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.core.composable.other.Toolbar
import com.storium.ui.screens.shop.model.DisplayMode
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.defaultIconSize
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopTitle

@Composable
fun ShopToolbar(
    displayMode: DisplayMode,
    onDisplayModeToggle: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Toolbar(
        title = stringResource(Res.string.shopTitle),
        modifier = modifier,
        trailingContent = {
            Image(
                painter = painterResource(
                    when (displayMode) {
                        DisplayMode.List -> AppIcons.ViewGrid
                        DisplayMode.Grid -> AppIcons.ViewList
                    },
                ),
                contentDescription = null,
                modifier = Modifier
                    .size(defaultIconSize)
                    .clickable { onDisplayModeToggle() },
            )
        },
    )
}

@Preview
@Composable
private fun ShopToolbarPreview() {
    StoriumTheme {
        ShopToolbar(displayMode = DisplayMode.List, onDisplayModeToggle = {})
    }
}
