package com.storium.ui.screens.personalinfo.validation

class PersonalInfoValidator {
    fun validate(
        firstName: String,
        lastName: String,
        email: String,
        previous: PersonalInfoValidationResult,
    ): PersonalInfoValidationResult {
        val isFirstNameValid = firstName.isNotBlank()
        val isLastNameValid = lastName.isNotBlank()
        val isEmailValid = email.isNotBlank()

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
