package com.storium.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.storium.domain.features.auth.usecase.IsUserLoggedInFlowUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn

class AppViewModel(isUserLoggedInFlowUseCase: IsUserLoggedInFlowUseCase) : ViewModel() {
    val isUserLoggedInFlow: StateFlow<Boolean> = isUserLoggedInFlowUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = false
    )
}
