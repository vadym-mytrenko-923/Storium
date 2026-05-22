package com.storium.ui.screens.shop.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.shop.ShopIntent
import com.storium.ui.screens.shop.ShopScreenState
import com.storium.ui.screens.shop.composable.preview.ShopPreviewUiModels
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.marginPrimary2X

private const val GRID_COLUMNS = 2

@Composable
fun ProductGrid(
    state: ShopScreenState,
    onIntent: (ShopIntent) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(GRID_COLUMNS),
        contentPadding = PaddingValues(marginPrimary2X),
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
private fun ProductGridPreview() {
    StoriumTheme {
        ProductGrid(
            state = ShopScreenState(products = ShopPreviewUiModels.products),
            onIntent = {},
        )
    }
}
