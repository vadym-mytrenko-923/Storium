package com.storium.ui.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE, INTENT, EFFECT>(initialState: STATE) : ViewModel() {

    private val _uiStateFlow = MutableStateFlow(initialState)
    val uiStateFlow: StateFlow<STATE> = _uiStateFlow.asStateFlow()

    private val _uiEffectChannel = Channel<EFFECT>(Channel.BUFFERED)
    val uiEffectFlow = _uiEffectChannel.receiveAsFlow()

    protected val currentState: STATE get() = _uiStateFlow.value

    fun onUserIntent(intent: INTENT) {
        reduceIntent(intent)
    }

    protected abstract fun reduceIntent(intent: INTENT)

    protected fun updateUiState(reducer: STATE.() -> STATE) {
        _uiStateFlow.value = currentState.reducer()
    }

    protected fun sendUiEffect(effect: EFFECT) {
        viewModelScope.launch {
            _uiEffectChannel.send(effect)
        }
    }

    protected fun launchViewModelScope(block: suspend () -> Unit) {
        viewModelScope.launch { block() }
    }
}
