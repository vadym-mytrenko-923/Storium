package com.storium.ui.screens.auth.login

class LoginValidator {
    fun validate(username: String, password: String): LoginValidationResult = LoginValidationResult(
        isUsernameValid = username.isNotBlank(),
        isPasswordValid = password.isNotEmpty(),
    )
}
