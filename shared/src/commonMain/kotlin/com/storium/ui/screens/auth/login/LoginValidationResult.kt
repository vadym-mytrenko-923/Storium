package com.storium.ui.screens.auth.login

data class LoginValidationResult(
    val isEmailValid: Boolean = true,
    val isPasswordValid: Boolean = true,
) {
    val isValid: Boolean get() = isEmailValid && isPasswordValid
}
