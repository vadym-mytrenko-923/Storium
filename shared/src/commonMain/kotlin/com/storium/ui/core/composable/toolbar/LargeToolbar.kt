package com.storium.ui.core.composable.toolbar

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.largeToolbarHeight
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.marginZero

@Composable
internal fun LargeToolbar(
    modifier: Modifier,
    title: String,
    topPadding: Dp,
    backgroundColor: Color,
    titleColor: Color,
    leadingContent: (@Composable () -> Unit)?,
    trailingContent: (@Composable () -> Unit)?,
) {
    val resolvedTitleColor = if (titleColor != Color.Unspecified) titleColor else MaterialTheme.appColors.textPrimary

    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = largeToolbarHeight)
            .background(backgroundColor)
            .padding(top = topPadding, bottom = marginPrimary2X)
            .padding(horizontal = marginPrimary2X),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingContent?.invoke()

        Text(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = if (leadingContent != null) marginPrimary2X else marginZero,
                    end = if (trailingContent != null) marginPrimary2X else marginZero,
                ),
            text = title,
            style = MaterialTheme.typography.headlineLarge,
            color = resolvedTitleColor,
        )

        trailingContent?.invoke()
    }
}

@Preview(showBackground = true)
@Composable
private fun LargeToolbarPreview() {
    StoriumTheme {
        Toolbar(title = "Shop")
    }
}
