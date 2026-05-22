package com.storium.ui.core.composable.other

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
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
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.marginPrimary3X
import com.storium.ui.theme.marginPrimary4X
import com.storium.ui.theme.marginZero

@Composable
fun Toolbar(
    title: String,
    modifier: Modifier = Modifier,
    topPadding: Dp = marginPrimary4X,
    backgroundColor: Color = Color.Transparent,
    titleColor: Color = Color.Unspecified,
    leadingContent: (@Composable () -> Unit)? = null,
    trailingContent: (@Composable () -> Unit)? = null,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .heightIn(min = defaultIconSize + marginPrimary3X + topPadding)
            .background(backgroundColor)
            .padding(
                start = marginPrimary2X,
                end = marginPrimary2X,
                bottom = marginPrimary3X,
                top = topPadding,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingContent?.invoke()

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(
                    start = if (leadingContent != null) marginPrimary2X else marginZero,
                    end = if (trailingContent != null) marginPrimary2X else marginZero,
                ),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = if (titleColor != Color.Unspecified) titleColor else MaterialTheme.appColors.textPrimary,
            )
        }

        trailingContent?.invoke()
    }
}

@Preview(showBackground = true)
@Composable
private fun ToolbarPreview() {
    StoriumTheme {
        Toolbar(title = "Shop")
    }
}

@Preview(showBackground = true)
@Composable
private fun ToolbarWithTrailingPreview() {
    StoriumTheme {
        Toolbar(
            title = "Shop",
            trailingContent = {
                Text("Action")
            },
        )
    }
}
