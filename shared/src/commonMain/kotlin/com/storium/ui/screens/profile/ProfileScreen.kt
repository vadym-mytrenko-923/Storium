package com.storium.ui.screens.profile

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.storium.ui.core.composable.button.BtnPrimary
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary2X
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.viewmodel.koinViewModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.profileBtnLogout
import storium.shared.generated.resources.profileTitle

@Composable
fun ProfileScreen(viewModel: ProfileViewModel = koinViewModel()) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Column(
            modifier = Modifier.padding(horizontal = marginPrimary2X),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = stringResource(Res.string.profileTitle),
                style = MaterialTheme.typography.headlineLarge,
                color = MaterialTheme.appColors.textPrimary,
            )

            Spacer(modifier = Modifier.height(marginPrimary2X))

            BtnPrimary(
                modifier = Modifier.fillMaxWidth(),
                text = stringResource(Res.string.profileBtnLogout),
                onClick = { viewModel.onUserIntent(ProfileIntent.LogoutClicked) },
            )
        }
    }
}
