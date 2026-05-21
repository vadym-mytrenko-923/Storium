package com.storium.ui.screens.auth.login

private val EMAIL_REGEX = Regex("[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")

class LoginValidator {
    fun validate(email: String, password: String): LoginValidationResult = LoginValidationResult(
        isEmailValid = EMAIL_REGEX.matches(email),
        isPasswordValid = password.isNotEmpty(),
    )
}
