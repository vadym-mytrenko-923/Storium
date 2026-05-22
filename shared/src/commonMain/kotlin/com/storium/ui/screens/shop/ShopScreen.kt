package com.storium.ui.screens.shop

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storium.ui.screens.shop.composable.ShopContent
import com.storium.ui.screens.shop.composable.preview.ShopPreviewUiModels
import com.storium.ui.theme.StoriumTheme
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ShopScreen(
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: ShopViewModel = koinViewModel(),
) {
    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    ShopContent(
        state = state,
        onIntent = viewModel::onUserIntent,
        paddingValues = paddingValues,
    )
}

@Preview
@Composable
private fun ShopScreenPreview() {
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
