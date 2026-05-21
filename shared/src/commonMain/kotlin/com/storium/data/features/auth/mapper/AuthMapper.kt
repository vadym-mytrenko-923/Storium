package com.storium.data.features.auth.mapper

import com.storium.data.features.auth.remote.model.LoginResponseDto
import com.storium.data.features.user.local.model.UserData

fun LoginResponseDto.toDataModel(): UserData = UserData(
    id = id,
    username = username,
    email = email,
    firstName = firstName,
    lastName = lastName,
    image = image,
)
