package com.storium.ui.screens.personalinfo

import com.storium.ui.screens.personalinfo.validation.PersonalInfoValidationResult

data class PersonalInfoScreenState(
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val initialFirstName: String = "",
    val initialLastName: String = "",
    val initialEmail: String = "",
    val validation: PersonalInfoValidationResult = PersonalInfoValidationResult(),
) {
    val isPersonalInfoChanged: Boolean
        get() = firstName != initialFirstName || lastName != initialLastName || email != initialEmail

    val isSaveButtonEnabled: Boolean
        get() = isPersonalInfoChanged && validation.isValid
}

sealed interface PersonalInfoIntent {
    data object BackClicked : PersonalInfoIntent
    data class FirstNameChanged(val value: String) : PersonalInfoIntent
    data class LastNameChanged(val value: String) : PersonalInfoIntent
    data class EmailChanged(val value: String) : PersonalInfoIntent
    data object SaveClicked : PersonalInfoIntent
}

sealed interface PersonalInfoEffect {
    data object UpdatedSuccessfully : PersonalInfoEffect
}
