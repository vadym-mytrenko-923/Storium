package com.storium.data.features.user.mapper

import com.storium.data.features.user.local.model.UserData
import com.storium.domain.features.auth.model.User

fun UserData.toDomainModel(): User = User(
    id = id,
    username = username,
    email = email,
    firstName = firstName,
    lastName = lastName,
    image = image,
)
