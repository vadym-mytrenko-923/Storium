package com.storium.ui.screens.product.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storium.ui.core.composable.other.FullscreenProgressIndicator
import com.storium.ui.core.composable.surface.ElevatedSurface
import com.storium.ui.core.composable.toolbar.Toolbar
import com.storium.ui.core.composable.toolbar.ToolbarStyle
import com.storium.ui.screens.product.details.composable.ProductDetailsContent
import com.storium.ui.screens.product.details.composable.preview.ProductDetailsPreviewUiModels
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.elevationToolbar
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.productDetailsTitle

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    ProductDetailsScreenContent(
        state = state,
        onIntent = viewModel::onUserIntent,
    )
}

@Composable
private fun ProductDetailsScreenContent(
    modifier: Modifier = Modifier,
    state: ProductDetailsScreenState,
    onIntent: (ProductDetailsIntent) -> Unit,
) {
    Scaffold(
        containerColor = MaterialTheme.appColors.background,
    ) { paddingValues ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(bottom = paddingValues.calculateBottomPadding()),
        ) {
            ElevatedSurface(
                shadowElevation = elevationToolbar,
            ) {
                Toolbar(
                    title = state.product?.title ?: stringResource(Res.string.productDetailsTitle),
                    style = ToolbarStyle.Small,
                    topPadding = paddingValues.calculateTopPadding(),
                    leadingContent = {
                        Image(
                            modifier = Modifier
                                .size(defaultIconSize)
                                .clickable { onIntent(ProductDetailsIntent.BackClicked) },
                            painter = painterResource(AppIcons.Back),
                            contentDescription = null,
                            colorFilter = ColorFilter.tint(MaterialTheme.appColors.iconPrimary),
                        )
                    },
                )
            }

            when {
                state.isLoading -> {
                    FullscreenProgressIndicator(backgroundColor = MaterialTheme.appColors.background)
                }

                state.product != null -> {
                    ProductDetailsContent(
                        modifier = Modifier.weight(1f),
                        product = state.product,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailsScreenContentLoadingPreview() {
    StoriumTheme {
        ProductDetailsScreenContent(
            state = ProductDetailsScreenState(),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailsScreenContentPreview() {
    StoriumTheme {
        ProductDetailsScreenContent(
            state = ProductDetailsScreenState(
                isLoading = false,
                product = ProductDetailsPreviewUiModels.productWithDiscount,
            ),
            onIntent = {},
        )
    }
}
