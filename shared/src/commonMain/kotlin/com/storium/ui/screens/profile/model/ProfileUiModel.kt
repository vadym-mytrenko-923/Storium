package com.storium.ui.screens.profile.model

import com.storium.ui.core.error.model.AppStringResource

data class ProfileUiModel(
    val displayName: AppStringResource,
    val email: String,
    val imageUrl: String,
)
