package com.storium.ui.screens.profile.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import coil3.compose.AsyncImage
import com.storium.ui.screens.profile.composable.preview.ProfilePreviewUiModels
import com.storium.ui.screens.profile.model.ProfileUiModel
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.profileAvatarSize
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun ProfileHeader(
    modifier: Modifier = Modifier,
    profile: ProfileUiModel,
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(marginPrimary2X),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        AsyncImage(
            modifier = Modifier
                .size(profileAvatarSize)
                .clip(CircleShape),
            model = profile.imageUrl,
            contentDescription = null,
            contentScale = ContentScale.Crop,
            error = painterResource(AppIcons.AvatarPlaceholder),
            placeholder = painterResource(AppIcons.AvatarPlaceholder),
        )

        Column(
            modifier = Modifier.padding(start = marginPrimary2X),
        ) {
            Text(
                text = stringResource(profile.displayName.res, *profile.displayName.args.toTypedArray()),
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.appColors.textPrimary,
            )

            Text(
                text = profile.email,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.appColors.textSecondary,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileHeaderPreview() {
    StoriumTheme {
        ProfileHeader(profile = ProfilePreviewUiModels.profile)
    }
}
