package com.storium.ui.screens.shop.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.screens.shop.ShopIntent
import com.storium.ui.screens.shop.ShopScreenState
import com.storium.ui.screens.shop.composable.preview.ShopPreviewUiModels
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.marginPrimary2X

@Composable
fun ProductList(
    modifier: Modifier = Modifier,
    state: ShopScreenState,
    onIntent: (ShopIntent) -> Unit,
) {
    LazyColumn(
        modifier = modifier,
        contentPadding = PaddingValues(marginPrimary2X),
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

@Preview(showBackground = true)
@Composable
private fun ProductListPreview() {
    StoriumTheme {
        ProductList(
            state = ShopScreenState(products = ShopPreviewUiModels.products),
            onIntent = {},
        )
    }
}
