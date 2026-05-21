package com.storium.ui.screens.auth.login.validation

data class LoginValidationResult(
    val isUsernameValid: Boolean = false,
    val isPasswordValid: Boolean = false,
    val showUsernameError: Boolean = false,
    val showPasswordError: Boolean = false,
) {
    val isValid: Boolean get() = isUsernameValid && isPasswordValid
}
