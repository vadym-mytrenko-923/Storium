package com.storium.ui.screens.auth.login

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.storium.ui.core.alert.LocalAppAlert
import com.storium.ui.core.composable.other.FullscreenProgressIndicator
import com.storium.ui.screens.auth.login.composable.LoginContent
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import kotlinx.coroutines.flow.collectLatest
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun LoginScreen(viewModel: LoginViewModel = koinViewModel()) {
    val appAlert = LocalAppAlert.current
    val state by viewModel.uiStateFlow.collectAsStateWithLifecycle()

    LaunchedEffect(viewModel) {
        viewModel.uiEffectFlow.collectLatest { effect ->
            when (effect) {
                is LoginEffect.ShowError -> appAlert.showAlert(
                    message = effect.error.messageResource.getString(),
                )
            }
        }
    }

    LoginScreenContent(
        state = state,
        onIntent = viewModel::onUserIntent,
    )
}

@Composable
private fun LoginScreenContent(
    state: LoginScreenState,
    onIntent: (LoginIntent) -> Unit,
) {
    Scaffold(containerColor = MaterialTheme.appColors.background) { paddingValues ->
        LoginContent(
            state = state,
            onIntent = onIntent,
            contentPadding = paddingValues,
        )

        if (state.isLoading) {
            FullscreenProgressIndicator()
        }
    }
}

@Preview
@Composable
private fun LoginScreenContentPreview() {
    StoriumTheme {
        LoginScreenContent(
            state = LoginScreenState(),
            onIntent = {},
        )
    }
}

@Preview
@Composable
private fun LoginScreenLoadingContentPreview() {
    StoriumTheme {
        LoginScreenContent(
            state = LoginScreenState(isLoading = true),
            onIntent = {},
        )
    }
}

@Preview
@Composable
private fun LoginScreenContentFilledPreview() {
    StoriumTheme {
        LoginScreenContent(
            state = LoginScreenState(username = "emilys", password = "emilyspass"),
            onIntent = {},
        )
    }
}
