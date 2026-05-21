package com.storium.ui.core.composable.icon

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.defaultIconSize
import org.jetbrains.compose.resources.painterResource

@Composable
fun InputCheckIcon(modifier: Modifier = Modifier) {
    Image(
        painter = painterResource(AppIcons.InputCheck),
        contentDescription = null,
        modifier = modifier.size(defaultIconSize),
    )
}

@Preview
@Composable
private fun InputCheckIconPreview() {
    StoriumTheme {
        InputCheckIcon()
    }
}
