package com.storium.ui.screens.auth.login.composable

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.storium.ui.core.composable.button.BtnPrimary
import com.storium.ui.core.composable.button.BtnTextPrimary
import com.storium.ui.core.composable.text.TextFieldPrimary
import com.storium.ui.screens.auth.login.LoginIntent
import com.storium.ui.screens.auth.login.LoginScreenState
import com.storium.ui.screens.auth.login.LoginValidationResult
import com.storium.ui.theme.AppIcons
import com.storium.ui.theme.StoriumTheme
import com.storium.ui.theme.appColors
import com.storium.ui.theme.marginPrimary
import com.storium.ui.theme.marginPrimary2X
import com.storium.ui.theme.marginPrimary4X
import com.storium.ui.theme.marginPrimary5X
import com.storium.ui.theme.marginZero
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.loginBtnForgotPassword
import storium.shared.generated.resources.loginBtnLogin
import storium.shared.generated.resources.loginPasswordError
import storium.shared.generated.resources.loginPasswordLabel
import storium.shared.generated.resources.loginTitle
import storium.shared.generated.resources.loginUsernameError
import storium.shared.generated.resources.loginUsernameLabel

@Composable
fun LoginContent(
    state: LoginScreenState,
    onIntent: (LoginIntent) -> Unit,
    contentPadding: PaddingValues,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = contentPadding.calculateTopPadding(),
                bottom = contentPadding.calculateBottomPadding(),
                start = marginPrimary2X,
                end = marginPrimary2X,
            ),
    ) {
        Spacer(modifier = Modifier.height(marginPrimary2X))

        Text(
            text = stringResource(Res.string.loginTitle),
            style = MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.appColors.textPrimary,
        )

        Spacer(modifier = Modifier.height(marginPrimary5X))

        TextFieldPrimary(
            value = state.username,
            onValueChange = { onIntent(LoginIntent.UsernameChanged(it)) },
            label = stringResource(Res.string.loginUsernameLabel),
            isError = !state.validation.isUsernameValid,
            errorText = stringResource(Res.string.loginUsernameError),
        )

        Spacer(modifier = Modifier.height(marginPrimary))

        TextFieldPrimary(
            value = state.password,
            onValueChange = { onIntent(LoginIntent.PasswordChanged(it)) },
            label = stringResource(Res.string.loginPasswordLabel),
            visualTransformation = PasswordVisualTransformation(),
            isError = !state.validation.isPasswordValid,
            errorText = stringResource(Res.string.loginPasswordError),
        )

        Spacer(modifier = Modifier.height(marginPrimary2X))

        BtnTextPrimary(
            text = stringResource(Res.string.loginBtnForgotPassword),
            onClick = { onIntent(LoginIntent.ForgotPasswordClicked) },
            modifier = Modifier.align(Alignment.End),
            trailingIcon = painterResource(AppIcons.ArrowRight),
        )

        Spacer(modifier = Modifier.height(marginPrimary4X))

        BtnPrimary(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(Res.string.loginBtnLogin),
            isEnabled = state.isLoginButtonEnabled,
            onClick = { onIntent(LoginIntent.LoginClicked) },
        )
    }
}

@Preview
@Composable
private fun LoginContentPreview() {
    StoriumTheme {
        LoginContent(
            state = LoginScreenState(),
            onIntent = {},
            contentPadding = PaddingValues(marginZero),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContentFilledPreview() {
    StoriumTheme {
        LoginContent(
            state = LoginScreenState(username = "emilys", password = "emilyspass"),
            onIntent = {},
            contentPadding = PaddingValues(marginZero),
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun LoginContentValidationErrorPreview() {
    StoriumTheme {
        LoginContent(
            state = LoginScreenState(
                validation = LoginValidationResult(isUsernameValid = false, isPasswordValid = false),
            ),
            onIntent = {},
            contentPadding = PaddingValues(marginZero),
        )
    }
}
