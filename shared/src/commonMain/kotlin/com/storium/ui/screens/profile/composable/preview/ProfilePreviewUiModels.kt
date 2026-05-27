package com.storium.ui.screens.profile.composable.preview

import com.storium.ui.core.error.model.AppStringResource
import com.storium.ui.screens.profile.model.ProfileUiModel
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.profileDisplayNameFormat

object ProfilePreviewUiModels {
    val profile = ProfileUiModel(
        displayName = AppStringResource(Res.string.profileDisplayNameFormat, listOf("Matilda", "Brown")),
        email = "matildabrown@mail.com",
        imageUrl = "",
    )
}
