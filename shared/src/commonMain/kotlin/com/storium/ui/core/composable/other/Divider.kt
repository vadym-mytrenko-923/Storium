package com.storium.ui.core.composable.other

import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.dividerWidth

@Composable
fun Divider(modifier: Modifier = Modifier) {
    HorizontalDivider(
        modifier = modifier,
        thickness = dividerWidth,
        color = MaterialTheme.appColors.divider,
    )
}

@Preview(showBackground = true)
@Composable
private fun DividerPreview() {
    StoriumTheme {
        Divider()
    }
}
