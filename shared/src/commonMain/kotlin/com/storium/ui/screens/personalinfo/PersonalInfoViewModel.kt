package com.storium.ui.screens.personalinfo

import com.storium.domain.features.user.usecase.GetUserDataFlowUseCase
import com.storium.domain.features.user.usecase.UpdateUserDataUseCase
import com.storium.ui.base.BaseViewModel
import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.screens.personalinfo.validation.PersonalInfoValidator

class PersonalInfoViewModel(
    private val appNavigator: AppNavigator,
    private val getUserDataFlowUseCase: GetUserDataFlowUseCase,
    private val updateUserDataUseCase: UpdateUserDataUseCase,
    private val validator: PersonalInfoValidator,
) : BaseViewModel<PersonalInfoScreenState, PersonalInfoIntent, PersonalInfoEffect>(
    PersonalInfoScreenState(),
) {
    init {
        observeUserData()
    }

    override suspend fun reduceIntent(intent: PersonalInfoIntent) {
        when (intent) {
            is PersonalInfoIntent.BackClicked -> appNavigator.back()
            is PersonalInfoIntent.FirstNameChanged -> onFieldChanged(firstName = intent.value)
            is PersonalInfoIntent.LastNameChanged -> onFieldChanged(lastName = intent.value)
            is PersonalInfoIntent.EmailChanged -> onFieldChanged(email = intent.value)
            is PersonalInfoIntent.SaveClicked -> onSaveProfileClicked()
        }
    }

    private fun onFieldChanged(
        firstName: String = currentState.firstName,
        lastName: String = currentState.lastName,
        email: String = currentState.email,
    ) {
        updateUiState {
            copy(
                firstName = firstName,
                lastName = lastName,
                email = email,
                validation = validator.validate(firstName, lastName, email, validation),
            )
        }
    }

    private fun observeUserData() {
        launchViewModelScope {
            getUserDataFlowUseCase().collect { user ->
                if (user != null) {
                    updateUiState {
                        copy(
                            firstName = user.firstName,
                            lastName = user.lastName,
                            email = user.email,
                            initialFirstName = user.firstName,
                            initialLastName = user.lastName,
                            initialEmail = user.email,
                        )
                    }
                }
            }
        }
    }

    private suspend fun onSaveProfileClicked() {
        if (!currentState.isSaveButtonEnabled) return

        updateUserDataUseCase(
            UpdateUserDataUseCase.Params(
                firstName = currentState.firstName,
                lastName = currentState.lastName,
                email = currentState.email,
            ),
        )
        sendUiEffect(PersonalInfoEffect.UpdatedSuccessfully)
    }
}
