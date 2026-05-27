package com.storium.domain.features.user

import com.storium.domain.features.auth.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val userDataFlow: Flow<User?>
}
