package com.storium.ui.screens.personalinfo.validation

data class PersonalInfoValidationResult(
    val isFirstNameValid: Boolean = true,
    val isLastNameValid: Boolean = true,
    val isEmailValid: Boolean = true,
    val showFirstNameError: Boolean = false,
    val showLastNameError: Boolean = false,
    val showEmailError: Boolean = false,
) {
    val isValid: Boolean get() = isFirstNameValid && isLastNameValid && isEmailValid
}
