package com.storium.data.features.auth.mapper

import com.storium.data.features.auth.remote.model.LoginResponseDto
import com.storium.domain.features.auth.model.User

fun LoginResponseDto.toDomainModel(): User = User(
    id = id,
    username = username,
    email = email,
    firstName = firstName,
    lastName = lastName,
    image = image,
)
