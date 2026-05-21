package com.storium.ui.screens.auth.login

private const val MIN_USERNAME_LENGTH = 4

class LoginValidator {
    fun validate(username: String, password: String, previous: LoginValidationResult): LoginValidationResult {
        val isUsernameValid = username.trim().length >= MIN_USERNAME_LENGTH
        val isPasswordValid = password.isNotEmpty()

        return LoginValidationResult(
            isUsernameValid = isUsernameValid,
            isPasswordValid = isPasswordValid,
            // Shows error when a field was valid and became invalid, keeps showing while invalid
            showUsernameError = !isUsernameValid && (previous.showUsernameError || previous.isUsernameValid),
            showPasswordError = !isPasswordValid && (previous.showPasswordError || previous.isPasswordValid),
        )
    }
}
