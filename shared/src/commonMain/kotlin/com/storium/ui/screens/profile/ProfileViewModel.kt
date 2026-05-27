package com.storium.ui.screens.profile

import com.storium.domain.features.auth.usecase.LogoutUseCase
import com.storium.domain.features.user.usecase.GetUserDataFlowUseCase
import com.storium.ui.base.BaseViewModel
import com.storium.ui.screens.profile.mapper.toProfileUiModel

class ProfileViewModel(
    private val getUserDataFlowUseCase: GetUserDataFlowUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel<ProfileScreenState, ProfileIntent, ProfileEffect>(ProfileScreenState()) {
    init {
        observeUserData()
    }

    override fun reduceIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.SettingsClicked -> Unit
            is ProfileIntent.LogoutClicked -> logout()
        }
    }

    private fun observeUserData() {
        launchViewModelScope {
            getUserDataFlowUseCase().collect { user ->
                updateUiState { copy(profile = user?.toProfileUiModel()) }
            }
        }
    }

    private fun logout() {
        launchViewModelScope {
            logoutUseCase()
        }
    }
}
