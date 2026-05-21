package com.storium.ui.core.alert.model

sealed interface AppAlertType {
    data object Error : AppAlertType
    data object Success : AppAlertType
}
