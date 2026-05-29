package com.storium.ui.screens.personalinfo.validation

private val EMAIL_REGEX = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")

class PersonalInfoValidator {
    fun validate(
        firstName: String,
        lastName: String,
        email: String,
        previous: PersonalInfoValidationResult,
    ): PersonalInfoValidationResult {
        val isFirstNameValid = firstName.isNotBlank()
        val isLastNameValid = lastName.isNotBlank()
        val isEmailValid = email.isNotBlank() && EMAIL_REGEX.matches(email.trim())

        return PersonalInfoValidationResult(
            isFirstNameValid = isFirstNameValid,
            isLastNameValid = isLastNameValid,
            isEmailValid = isEmailValid,
            showFirstNameError = !isFirstNameValid && (previous.showFirstNameError || previous.isFirstNameValid),
            showLastNameError = !isLastNameValid && (previous.showLastNameError || previous.isLastNameValid),
            showEmailError = !isEmailValid && (previous.showEmailError || previous.isEmailValid),
        )
    }
}
