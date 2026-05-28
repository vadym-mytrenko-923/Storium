package com.storium.ui.screens.profile.composable

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.defaultIconSize
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.marginPrimaryHalf
import com.storium.ui.theme.profileMenuItemHeight
import org.jetbrains.compose.resources.painterResource

@Composable
fun ProfileMenuItem(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(profileMenuItemHeight)
            .clickable(onClick = onClick)
            .padding(horizontal = marginPrimary2X),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Column(
            modifier = Modifier.weight(1f),
            verticalArrangement = Arrangement.spacedBy(marginPrimaryHalf),
        ) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.appColors.textPrimary,
            )

            Text(
                text = subtitle,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.appColors.textSecondary,
            )
        }

        Image(
            modifier = Modifier.size(defaultIconSize),
            painter = painterResource(AppIcons.ArrowRight),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.appColors.iconPrimary),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileMenuItemPreview() {
    StoriumTheme {
        ProfileMenuItem(
            title = "Settings",
            subtitle = "Notifications, password",
            onClick = {},
        )
    }
}
