package com.storium.ui.core.alert.model

import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarVisuals

data class AppAlertParams(
    override val message: String,
    override val actionLabel: String? = null,
    override val duration: SnackbarDuration = SnackbarDuration.Short,
    override val withDismissAction: Boolean = false,
    val title: String? = null,
    val description: String? = null,
    val type: AppAlertType = AppAlertType.Error,
) : SnackbarVisuals
