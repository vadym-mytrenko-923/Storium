package com.storium.ui.screens.auth.login

import com.storium.domain.features.auth.model.LoginParams
import com.storium.domain.features.auth.usecase.LoginUseCase
import com.storium.domain.features.auth.usecase.SetUserLoggedInUseCase
import com.storium.ui.base.BaseViewModel
import com.storium.ui.core.error.UiErrorParser

class LoginViewModel(
    private val loginUseCase: LoginUseCase,
    private val setUserLoggedInUseCase: SetUserLoggedInUseCase,
    private val loginValidator: LoginValidator,
    private val uiErrorParser: UiErrorParser,
) : BaseViewModel<LoginScreenState, LoginIntent, LoginEffect>(LoginScreenState()) {

    override fun reduceIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.EmailChanged -> onEmailChanged(intent.value)
            is LoginIntent.PasswordChanged -> onPasswordChanged(intent.value)
            is LoginIntent.LoginClicked -> login()
            is LoginIntent.ForgotPasswordClicked -> Unit
        }
    }

    private fun onEmailChanged(value: String) {
        updateUiState {
            copy(
                email = value,
                validation = if (!validation.isValid) loginValidator.validate(value, password) else validation,
            )
        }
    }

    private fun onPasswordChanged(value: String) {
        updateUiState {
            copy(
                password = value,
                validation = if (!validation.isValid) loginValidator.validate(email, value) else validation,
            )
        }
    }

    private fun login() {
        val validationResult = loginValidator.validate(currentState.email, currentState.password)
        updateUiState { copy(validation = validationResult) }
        if (!validationResult.isValid) return

        launchViewModelScope {
            updateUiState { copy(isLoading = true) }
            loginUseCase(LoginParams(username = currentState.email, password = currentState.password))
                .onSuccess {
                    setUserLoggedInUseCase()
                }
                .onFailure { error ->
                    updateUiState { copy(isLoading = false) }
                    sendUiEffect(LoginEffect.ShowError(uiErrorParser.parseError(error)))
                }
        }
    }
}
