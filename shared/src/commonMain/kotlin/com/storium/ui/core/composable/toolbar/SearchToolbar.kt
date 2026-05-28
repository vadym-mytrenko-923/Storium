package com.storium.ui.core.composable.toolbar

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.text.input.KeyboardCapitalization
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.largeToolbarHeight
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.smallToolbarHeight
import org.jetbrains.compose.resources.painterResource

@Composable
fun SearchToolbar(
    modifier: Modifier = Modifier,
    query: String,
    style: ToolbarStyle = ToolbarStyle.Large,
    topPadding: Dp = marginPrimary2X,
    backgroundColor: Color = MaterialTheme.appColors.cardBackground,
    placeholder: String = "",
    onQueryChanged: (String) -> Unit,
    onCloseClicked: () -> Unit,
) {
    val focusRequester = remember { FocusRequester() }
    var searchQuery by remember { mutableStateOf(query) }
    val minHeight = when (style) {
        ToolbarStyle.Large -> largeToolbarHeight
        ToolbarStyle.Small -> smallToolbarHeight
    }

    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }

    LaunchedEffect(searchQuery) {
        onQueryChanged(searchQuery)
    }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .defaultMinSize(minHeight = minHeight)
            .background(color = backgroundColor)
            .padding(top = topPadding, bottom = marginPrimary2X)
            .padding(horizontal = marginPrimary2X),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            modifier = Modifier
                .size(defaultIconSize)
                .clickable { onCloseClicked() },
            painter = painterResource(AppIcons.Back),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.appColors.iconPrimary),
        )

        BasicTextField(
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = marginPrimary2X)
                .focusRequester(focusRequester),
            value = searchQuery,
            onValueChange = { searchQuery = it },
            textStyle = MaterialTheme.typography.bodyLarge.copy(
                color = MaterialTheme.appColors.textPrimary,
            ),
            singleLine = true,
            cursorBrush = SolidColor(MaterialTheme.appColors.primary),
            keyboardOptions = KeyboardOptions(capitalization = KeyboardCapitalization.Words),
            decorationBox = { innerTextField ->
                if (searchQuery.isEmpty() && placeholder.isNotEmpty()) {
                    Text(
                        text = placeholder,
                        style = MaterialTheme.typography.bodyLarge,
                        color = MaterialTheme.appColors.textSecondary,
                    )
                }

                innerTextField()
            },
        )

        if (searchQuery.isNotEmpty()) {
            Image(
                modifier = Modifier
                    .size(defaultIconSize)
                    .clickable { searchQuery = "" },
                painter = painterResource(AppIcons.Close),
                contentDescription = null,
                colorFilter = ColorFilter.tint(MaterialTheme.appColors.iconPrimary),
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchToolbarLargePreview() {
    StoriumTheme {
        SearchToolbar(
            query = "Phone",
            placeholder = "Search products...",
            onQueryChanged = {},
            onCloseClicked = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SearchToolbarSmallPreview() {
    StoriumTheme {
        SearchToolbar(
            query = "",
            style = ToolbarStyle.Small,
            placeholder = "Search...",
            onQueryChanged = {},
            onCloseClicked = {},
        )
    }
}
