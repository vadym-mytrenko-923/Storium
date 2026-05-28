package com.storium.ui.core.composable.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary2X
import org.jetbrains.compose.resources.painterResource

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

@Preview(showBackground = true)
@Composable
private fun ToolbarLargePreview() {
    StoriumTheme {
        Toolbar(title = "Shop")
    }
}

@Preview(showBackground = true)
@Composable
private fun ToolbarLargeWithTrailingPreview() {
    StoriumTheme {
        Toolbar(
            title = "Shop",
            trailingContent = { Text("Action") },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ToolbarSmallPreview() {
    StoriumTheme {
        Toolbar(title = "Product Details", style = ToolbarStyle.Small)
    }
}

@Preview(showBackground = true)
@Composable
private fun ToolbarSmallWithLeadingPreview() {
    StoriumTheme {
        Toolbar(
            title = "Product Details",
            style = ToolbarStyle.Small,
            leadingContent = {
                Image(
                    modifier = Modifier.size(defaultIconSize),
                    painter = painterResource(AppIcons.Back),
                    contentDescription = null,
                )
            },
        )
    }
}
