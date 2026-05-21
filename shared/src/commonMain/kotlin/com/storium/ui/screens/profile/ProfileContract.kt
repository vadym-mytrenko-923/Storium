package com.storium.ui.screens.profile

data class ProfileScreenState(
    val isLoading: Boolean = false,
)

sealed interface ProfileIntent {
    data object LogoutClicked : ProfileIntent
}

sealed interface ProfileEffect
