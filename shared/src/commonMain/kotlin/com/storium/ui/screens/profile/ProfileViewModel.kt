package com.storium.ui.screens.profile

import com.storium.domain.features.auth.usecase.LogoutUseCase
import com.storium.ui.base.BaseViewModel

class ProfileViewModel(
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel<ProfileScreenState, ProfileIntent, ProfileEffect>(ProfileScreenState()) {

    override fun reduceIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.LogoutClicked -> logout()
        }
    }

    private fun logout() {
        launchViewModelScope {
            logoutUseCase()
        }
    }
}
