package com.storium.ui.screens.shop.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.domain.features.product.model.Category
import com.storium.ui.core.composable.other.FullscreenProgressIndicator
import com.storium.ui.core.composable.other.Toolbar
import com.storium.ui.screens.shop.ShopIntent
import com.storium.ui.screens.shop.ShopScreenState
import com.storium.ui.screens.shop.composable.preview.ShopPreviewUiModels
import com.storium.ui.screens.shop.model.DisplayMode
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary2X
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopEmptyState
import storium.shared.generated.resources.shopTitle

private const val GRID_COLUMNS = 2

@Composable
fun ShopContent(
    state: ShopScreenState,
    onIntent: (ShopIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Toolbar(
            modifier = modifier,
            title = stringResource(Res.string.shopTitle),
            trailingContent = {
                Image(
                    modifier = Modifier
                        .size(defaultIconSize)
                        .clickable { onIntent(ShopIntent.DisplayModeToggled) },
                    painter = painterResource(
                        when (state.displayMode) {
                            DisplayMode.List -> AppIcons.ViewGrid
                            DisplayMode.Grid -> AppIcons.ViewList
                        },
                    ),
                    contentDescription = null,
                )
            },
        )

        if (!state.isLoading && state.categories.isNotEmpty()) {
            CategoryChipRow(
                categories = state.categories,
                onCategoryClicked = { category ->
                    onIntent(ShopIntent.CategoryToggled(Category(id = category.id, name = category.name)))
                },
            )

            Spacer(modifier = Modifier.height(marginPrimary))
        }

        Box(modifier = Modifier.weight(1f)) {
            when {
                state.isLoading -> {
                    FullscreenProgressIndicator(
                        backgroundColor = MaterialTheme.appColors.background,
                    )
                }

                state.products.isEmpty() -> {
                    Box(
                        modifier = Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center,
                    ) {
                        Text(
                            text = stringResource(Res.string.shopEmptyState),
                            style = MaterialTheme.typography.bodyLarge,
                            color = MaterialTheme.appColors.textSecondary,
                        )
                    }
                }

                else -> {
                    AnimatedContent(
                        targetState = state.displayMode,
                        transitionSpec = { fadeIn() togetherWith fadeOut() },
                        label = "displayModeTransition",
                    ) { displayMode ->
                        when (displayMode) {
                            DisplayMode.List -> ProductList(state = state, onIntent = onIntent)
                            DisplayMode.Grid -> ProductGrid(state = state, onIntent = onIntent)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ProductList(
    state: ShopScreenState,
    onIntent: (ShopIntent) -> Unit,
) {
    LazyColumn(
        contentPadding = PaddingValues(horizontal = marginPrimary2X, vertical = marginPrimary2X),
        verticalArrangement = Arrangement.spacedBy(marginPrimary2X),
    ) {
        items(state.products, key = { it.id }) { product ->
            ProductListItem(
                product = product,
                onClick = { onIntent(ShopIntent.ProductClicked(product.id)) },
            )
        }
    }
}

@Composable
private fun ProductGrid(
    state: ShopScreenState,
    onIntent: (ShopIntent) -> Unit,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(GRID_COLUMNS),
        contentPadding = PaddingValues(horizontal = marginPrimary2X, vertical = marginPrimary2X),
        horizontalArrangement = Arrangement.spacedBy(marginPrimary2X),
        verticalArrangement = Arrangement.spacedBy(marginPrimary2X),
    ) {
        items(state.products, key = { it.id }) { product ->
            ProductGridItem(
                product = product,
                onClick = { onIntent(ShopIntent.ProductClicked(product.id)) },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopContentPreview() {
    StoriumTheme {
        ShopContent(
            state = ShopScreenState(
                products = ShopPreviewUiModels.products,
                categories = ShopPreviewUiModels.categories,
                isLoading = false,
            ),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ShopContentEmptyPreview() {
    StoriumTheme {
        ShopContent(
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
private fun ShopContentLoadingPreview() {
    StoriumTheme {
        ShopContent(
            state = ShopScreenState(),
            onIntent = {},
        )
    }
}
