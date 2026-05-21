package com.storium.ui.screens.auth.login

data class LoginValidationResult(
    val isUsernameValid: Boolean = true,
    val isPasswordValid: Boolean = true,
) {
    val isValid: Boolean get() = isUsernameValid && isPasswordValid
}
