package com.storium.ui.core.composable.toolbar

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import com.storium.ui.theme.marginPrimary2X

@Composable
fun Toolbar(
    modifier: Modifier = Modifier,
    title: String,
    style: ToolbarStyle = ToolbarStyle.Large,
    topPadding: Dp = marginPrimary2X,
    backgroundColor: Color = Color.Transparent,
    titleColor: Color = Color.Unspecified,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    when (style) {
        ToolbarStyle.Large -> LargeToolbar(
            modifier = modifier,
            title = title,
            topPadding = topPadding,
            backgroundColor = backgroundColor,
            titleColor = titleColor,
            leadingContent = leadingContent,
            trailingContent = trailingContent,
        )

        ToolbarStyle.Small -> SmallToolbar(
            modifier = modifier,
            title = title,
            topPadding = topPadding,
            backgroundColor = backgroundColor,
            titleColor = titleColor,
            leadingContent = leadingContent,
            trailingContent = trailingContent,
        )
    }
}
