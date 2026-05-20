package com.storium.ui.screens.main

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.storium.domain.features.auth.usecase.LogoutUseCase
import com.storium.ui.core.composable.button.BtnPrimary
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary2X
import kotlinx.coroutines.launch
import org.jetbrains.compose.resources.stringResource
import org.koin.compose.koinInject
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.mainBtnLogout

@Composable
fun MainScreen(logoutUseCase: LogoutUseCase = koinInject()) {
    val scope = rememberCoroutineScope()

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.appColors.background)
            .safeContentPadding(),
        contentAlignment = Alignment.Center,
    ) {
        BtnPrimary(
            modifier = Modifier.fillMaxWidth().padding(horizontal = marginPrimary2X),
            text = stringResource(Res.string.mainBtnLogout),
            onClick = { scope.launch { logoutUseCase() } },
        )
    }
}
