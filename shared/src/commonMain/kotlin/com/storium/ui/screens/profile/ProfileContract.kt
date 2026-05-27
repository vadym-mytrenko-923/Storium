package com.storium.ui.screens.profile

import com.storium.ui.screens.profile.model.ProfileUiModel

data class ProfileScreenState(
    val profile: ProfileUiModel? = null,
)

sealed interface ProfileIntent {
    data object SettingsClicked : ProfileIntent
    data object LogoutClicked : ProfileIntent
}

sealed interface ProfileEffect
