package com.storium.ui.screens.auth.login

import com.storium.domain.features.auth.usecase.SetUserLoggedInUseCase
import com.storium.ui.base.BaseViewModel

class LoginViewModel(private val setUserLoggedInUseCase: SetUserLoggedInUseCase) :
    BaseViewModel<LoginScreenState, LoginIntent, LoginEffect>(LoginScreenState()) {

    override fun reduceIntent(intent: LoginIntent) {
        when (intent) {
            is LoginIntent.LoginClicked -> login()
        }
    }

    private fun login() {
        launchViewModelScope {
            setUserLoggedInUseCase()
        }
    }
}
