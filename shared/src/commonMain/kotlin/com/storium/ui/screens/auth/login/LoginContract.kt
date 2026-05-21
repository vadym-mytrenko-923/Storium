package com.storium.ui.screens.auth.login

import com.storium.ui.core.error.model.UiError

data class LoginScreenState(
    val username: String = "",
    val password: String = "",
    val isLoading: Boolean = false,
    val validation: LoginValidationResult = LoginValidationResult(),
) {
    val isLoginButtonEnabled: Boolean get() = username.isNotBlank() && password.isNotEmpty() && !isLoading
}

sealed interface LoginIntent {
    data class UsernameChanged(val value: String) : LoginIntent
    data class PasswordChanged(val value: String) : LoginIntent
    data object LoginClicked : LoginIntent
    data object ForgotPasswordClicked : LoginIntent
}

sealed interface LoginEffect {
    data class ShowError(val error: UiError) : LoginEffect
}
