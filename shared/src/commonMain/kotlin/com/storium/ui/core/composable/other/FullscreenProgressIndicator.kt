package com.storium.ui.core.composable.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors

@Composable
fun FullscreenProgressIndicator(
    modifier: Modifier = Modifier,
    backgroundColor: Color = MaterialTheme.appColors.fullscreenLoaderBackground,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        contentAlignment = Alignment.Center,
    ) {
        CircularProgressIndicator(color = MaterialTheme.appColors.primary)
    }
}

@Preview
@Composable
private fun FullscreenProgressIndicatorPreview() {
    StoriumTheme {
        FullscreenProgressIndicator()
    }
}
