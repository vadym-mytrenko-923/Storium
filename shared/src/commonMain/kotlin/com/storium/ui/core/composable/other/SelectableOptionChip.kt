package com.storium.ui.core.composable.other

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.containerShapeDefault
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.selectableChipBorderWidth
import com.storium.ui.theme.selectableChipHeight
import org.jetbrains.compose.resources.painterResource

@Composable
fun SelectableOptionChip(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean,
    leadingContent: (@Composable () -> Unit)? = null,
    onClick: () -> Unit,
) {
    val borderColor = if (isSelected) MaterialTheme.appColors.primary else MaterialTheme.appColors.borderDefault
    val backgroundColor = if (isSelected) Color.Transparent else MaterialTheme.appColors.background

    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(selectableChipHeight)
            .border(
                width = selectableChipBorderWidth,
                color = borderColor,
                shape = containerShapeDefault,
            )
            .background(
                color = backgroundColor,
                shape = containerShapeDefault,
            )
            .clickable(onClick = onClick)
            .padding(horizontal = marginPrimary2X),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        leadingContent?.let {
            it()
            Spacer(modifier = Modifier.width(marginPrimary))
        }

        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.appColors.textPrimary,
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectableOptionChipSelectedPreview() {
    StoriumTheme {
        SelectableOptionChip(
            text = "Light",
            isSelected = true,
            leadingContent = {
                Image(
                    modifier = Modifier.size(defaultIconSize),
                    painter = painterResource(AppIcons.ThemeLight),
                    contentDescription = null,
                )
            },
            onClick = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SelectableOptionChipUnselectedPreview() {
    StoriumTheme {
        SelectableOptionChip(
            text = "Dark",
            isSelected = false,
            leadingContent = {
                Image(
                    modifier = Modifier.size(defaultIconSize),
                    painter = painterResource(AppIcons.ThemeDark),
                    contentDescription = null,
                )
            },
            onClick = {},
        )
    }
}
