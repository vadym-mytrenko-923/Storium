package com.storium.ui.screens.auth.login

data class LoginScreenState(val isLoading: Boolean = false)

sealed interface LoginIntent {
    data object LoginClicked : LoginIntent
}

sealed interface LoginEffect
