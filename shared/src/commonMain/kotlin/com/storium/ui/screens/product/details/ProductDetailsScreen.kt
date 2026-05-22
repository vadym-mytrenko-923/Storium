package com.storium.ui.screens.product.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.core.composable.other.Toolbar
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.productDetailsComingSoon
import storium.shared.generated.resources.productDetailsTitle

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = koinViewModel(),
) {
    ProductDetailsContent(
        onIntent = viewModel::onUserIntent,
    )
}

@Composable
private fun ProductDetailsContent(
    modifier: Modifier = Modifier,
    onIntent: (ProductDetailsIntent) -> Unit,
) {
    Column(modifier = modifier.fillMaxSize()) {
        Toolbar(
            title = stringResource(Res.string.productDetailsTitle),
            leadingContent = {
                Image(
                    modifier = Modifier
                        .size(defaultIconSize)
                        .clickable { onIntent(ProductDetailsIntent.BackClicked) },
                    painter = painterResource(AppIcons.ArrowBack),
                    contentDescription = null,
                )
            },
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(Res.string.productDetailsComingSoon),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.appColors.textSecondary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProductDetailsContentPreview() {
    StoriumTheme {
        ProductDetailsContent(onIntent = {})
    }
}
