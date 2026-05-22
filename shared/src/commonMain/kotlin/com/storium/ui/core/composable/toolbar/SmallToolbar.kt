package com.storium.ui.core.composable.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.smallToolbarHeight
import org.jetbrains.compose.resources.painterResource

@Composable
internal fun SmallToolbar(
    modifier: Modifier,
    title: String,
    topPadding: Dp,
    backgroundColor: Color,
    titleColor: Color,
    leadingContent: (@Composable () -> Unit)?,
    trailingContent: (@Composable () -> Unit)?,
) {
    val resolvedTitleColor = if (titleColor != Color.Unspecified) titleColor else MaterialTheme.appColors.textPrimary

    Box(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = smallToolbarHeight)
            .background(backgroundColor)
            .padding(top = topPadding, bottom = marginPrimary2X)
            .padding(horizontal = marginPrimary2X),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Center,
            color = resolvedTitleColor,
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            leadingContent?.invoke()

            Box(modifier = Modifier.weight(1f))

            trailingContent?.invoke()
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallToolbarPreview() {
    StoriumTheme {
        Toolbar(title = "Product Details", style = ToolbarStyle.Small)
    }
}

@Preview(showBackground = true)
@Composable
private fun SmallToolbarWithLeadingPreview() {
    StoriumTheme {
        Toolbar(
            title = "Product Details",
            style = ToolbarStyle.Small,
            leadingContent = {
                Image(
                    modifier = Modifier.size(defaultIconSize),
                    painter = painterResource(AppIcons.ArrowBack),
                    contentDescription = null,
                )
            },
        )
    }
}
