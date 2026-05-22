package com.storium.ui.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.storium.ui.core.composable.button.BtnPrimary
import com.storium.ui.core.composable.toolbar.Toolbar
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary2X
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.profileBtnLogout
import storium.shared.generated.resources.profileComingSoon
import storium.shared.generated.resources.profileTitle

@Composable
fun ProfileScreen(
    paddingValues: PaddingValues = PaddingValues(),
    viewModel: ProfileViewModel = koinViewModel(),
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Toolbar(
            modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
            title = stringResource(Res.string.profileTitle),
        )

        Box(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth(),
            contentAlignment = Alignment.Center,
        ) {
            Text(
                text = stringResource(Res.string.profileComingSoon),
                style = MaterialTheme.typography.bodyLarge,
                color = MaterialTheme.appColors.textSecondary,
            )
        }

        BtnPrimary(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = marginPrimary2X)
                .padding(bottom = marginPrimary2X),
            text = stringResource(Res.string.profileBtnLogout),
            onClick = { viewModel.onUserIntent(ProfileIntent.LogoutClicked) },
        )
    }
}
