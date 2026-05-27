package com.storium.ui.screens.profile.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.core.composable.button.BtnPrimary
import com.storium.ui.core.composable.toolbar.Toolbar
import com.storium.ui.screens.profile.ProfileIntent
import com.storium.ui.screens.profile.ProfileScreenState
import com.storium.ui.screens.profile.composable.preview.ProfilePreviewUiModels
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.marginPrimary3_5X
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.profileBtnLogout
import storium.shared.generated.resources.profileSettingsSubtitle
import storium.shared.generated.resources.profileSettingsTitle
import storium.shared.generated.resources.profileTitle

@Composable
fun ProfileContent(
    modifier: Modifier = Modifier,
    state: ProfileScreenState,
    onIntent: (ProfileIntent) -> Unit,
    paddingValues: PaddingValues = PaddingValues(),
) {
    Column(modifier = modifier.fillMaxSize()) {
        Toolbar(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            title = stringResource(Res.string.profileTitle),
        )

        state.profile?.let { profile ->
            ProfileHeader(profile = profile)
            Spacer(modifier = Modifier.height(marginPrimary3_5X))
        }

        ProfileMenuItem(
            title = stringResource(Res.string.profileSettingsTitle),
            subtitle = stringResource(Res.string.profileSettingsSubtitle),
            onClick = { onIntent(ProfileIntent.SettingsClicked) },
        )

        Spacer(modifier = Modifier.weight(1f))

        BtnPrimary(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = marginPrimary2X)
                .padding(bottom = marginPrimary2X),
            text = stringResource(Res.string.profileBtnLogout),
            onClick = { onIntent(ProfileIntent.LogoutClicked) },
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileContentPreview() {
    StoriumTheme {
        ProfileContent(
            state = ProfileScreenState(
                profile = ProfilePreviewUiModels.profile,
            ),
            onIntent = {},
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfileContentEmptyPreview() {
    StoriumTheme {
        ProfileContent(
            state = ProfileScreenState(),
            onIntent = {},
        )
    }
}
