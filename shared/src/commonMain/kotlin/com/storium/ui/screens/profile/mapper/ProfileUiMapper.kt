package com.storium.ui.screens.profile.mapper

import com.storium.domain.features.auth.model.User
import com.storium.ui.core.error.model.AppStringResource
import com.storium.ui.screens.profile.model.ProfileUiModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.profileDisplayNameFormat

fun User.toProfileUiModel(): ProfileUiModel = ProfileUiModel(
    displayName = AppStringResource(Res.string.profileDisplayNameFormat, listOf(firstName, lastName)),
    email = email,
    imageUrl = image,
)
