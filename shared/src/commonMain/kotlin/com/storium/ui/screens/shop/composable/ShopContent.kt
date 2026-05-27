package com.storium.ui.screens.shop.composable

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.pulltorefresh.PullToRefreshBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.core.composable.empty.EmptyStateView
import com.storium.ui.core.composable.other.FullscreenProgressIndicator
import com.storium.ui.screens.shop.ShopIntent
import com.storium.ui.screens.shop.ShopScreenState
import com.storium.ui.screens.shop.composable.grid.ProductGrid
import com.storium.ui.screens.shop.composable.list.ProductList
import com.storium.ui.screens.shop.composable.preview.ShopPreviewUiModels
import com.storium.ui.screens.shop.model.DisplayMode
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.shopEmptyState

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShopContent(
    modifier: Modifier = Modifier,
    state: ShopScreenState,
    onIntent: (ShopIntent) -> Unit,
    paddingValues: PaddingValues = PaddingValues(),
) {
    Column(modifier = modifier.fillMaxSize()) {
        ShopHeader(
            state = state,
            onIntent = onIntent,
            paddingValues = paddingValues,
        )

        PullToRefreshBox(
            modifier = Modifier.weight(1f),
            isRefreshing = state.isRefreshing,
            onRefresh = { onIntent(ShopIntent.PullToRefresh) },
        ) {
            when {
                state.isLoading && !state.isRefreshing -> {
                    FullscreenProgressIndicator(backgroundColor = MaterialTheme.appColors.background)
                }

                state.products.isEmpty() -> {
                    EmptyStateView(message = stringResource(Res.string.shopEmptyState))
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
private fun ShopContentSearchPreview() {
    StoriumTheme {
        ShopContent(
            state = ShopScreenState(
                products = ShopPreviewUiModels.products,
                categories = ShopPreviewUiModels.categories,
                isLoading = false,
                isSearchActive = true,
                searchQuery = "Pullover",
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
