package com.storium.ui.screens.shop.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.core.composable.surface.ElevatedSurface
import com.storium.ui.core.composable.toolbar.SearchToolbar
import com.storium.ui.core.composable.toolbar.Toolbar
import com.storium.ui.core.composable.toolbar.ToolbarStyle
import com.storium.ui.screens.shop.ShopIntent
import com.storium.ui.screens.shop.ShopScreenState
import com.storium.ui.screens.shop.composable.preview.ShopPreviewUiModels
import com.storium.ui.screens.shop.model.DisplayMode
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary2X
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopSearchPlaceholder
import storium.shared.generated.resources.shopTitle

@Composable
fun ShopHeader(
    modifier: Modifier = Modifier,
    state: ShopScreenState,
    onIntent: (ShopIntent) -> Unit,
    paddingValues: PaddingValues = PaddingValues(),
) {
    ElevatedSurface(modifier = modifier) {
        Column {
            if (state.isSearchActive) {
                SearchToolbar(
                    query = state.searchQuery,
                    style = ToolbarStyle.Large,
                    topPadding = paddingValues.calculateTopPadding() + marginPrimary2X,
                    placeholder = stringResource(Res.string.shopSearchPlaceholder),
                    onQueryChanged = { onIntent(ShopIntent.SearchQueryChanged(it)) },
                    onCloseClicked = { onIntent(ShopIntent.SearchToggled) },
                )
            } else {
                Toolbar(
                    title = stringResource(Res.string.shopTitle),
                    topPadding = paddingValues.calculateTopPadding() + marginPrimary2X,
                    trailingContent = {
                        Image(
                            modifier = Modifier
                                .size(defaultIconSize)
                                .clickable { onIntent(ShopIntent.SearchToggled) },
                            painter = painterResource(AppIcons.Search),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.appColors.iconPrimary),
                        )

                        Image(
                            modifier = Modifier
                                .padding(start = marginPrimary2X)
                                .size(defaultIconSize)
                                .clickable { onIntent(ShopIntent.DisplayModeToggled) },
                            painter = painterResource(
                                when (state.displayMode) {
                                    DisplayMode.List -> AppIcons.ViewGrid
                                    DisplayMode.Grid -> AppIcons.ViewList
                                },
                            ),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.appColors.iconPrimary),
                        )
                    },
                )
            }

            if (!state.isLoading && state.categories.isNotEmpty()) {
                CategoryChipRow(
                    categories = state.categories,
                    onCategoryClicked = { category ->
                        onIntent(ShopIntent.CategoryToggled(category.id))
                    },
                )

                Spacer(modifier = Modifier.height(marginPrimary2X))
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopHeaderPreview() {
    StoriumTheme {
        ShopHeader(
            state = ShopScreenState(
                categories = ShopPreviewUiModels.categories,
                isLoading = false,
            ),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopHeaderSearchPreview() {
    StoriumTheme {
        ShopHeader(
            state = ShopScreenState(
                categories = ShopPreviewUiModels.categories,
                isLoading = false,
                isSearchActive = true,
                searchQuery = "Phone",
            ),
            onIntent = {},
        )
    }
}
