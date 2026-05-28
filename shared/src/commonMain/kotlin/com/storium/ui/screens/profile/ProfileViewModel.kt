package com.storium.ui.screens.profile

import com.storium.domain.features.auth.usecase.LogoutUseCase
import com.storium.domain.features.user.usecase.GetUserDataFlowUseCase
import com.storium.ui.base.BaseViewModel
import com.storium.ui.navigation.app.AppNavigator
import com.storium.ui.navigation.model.AppNavRoute
import com.storium.ui.screens.profile.mapper.toProfileUiModel

class ProfileViewModel(
    private val appNavigator: AppNavigator,
    private val getUserDataFlowUseCase: GetUserDataFlowUseCase,
    private val logoutUseCase: LogoutUseCase,
) : BaseViewModel<ProfileScreenState, ProfileIntent, ProfileEffect>(ProfileScreenState()) {
    init {
        observeUserData()
    }

    override suspend fun reduceIntent(intent: ProfileIntent) {
        when (intent) {
            is ProfileIntent.PersonalInfoClicked -> appNavigator.navigateTo(AppNavRoute.PersonalInfo)
            is ProfileIntent.SettingsClicked -> appNavigator.navigateTo(AppNavRoute.Settings)
            is ProfileIntent.LogoutClicked -> logoutUseCase()
        }
    }

    private fun observeUserData() {
        launchViewModelScope {
            getUserDataFlowUseCase().collect { user ->
                updateUiState { copy(profile = user?.toProfileUiModel()) }
            }
        }
    }
}
