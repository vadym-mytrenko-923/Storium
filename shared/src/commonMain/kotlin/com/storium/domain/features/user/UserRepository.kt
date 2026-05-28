package com.storium.domain.features.user

import com.storium.domain.features.auth.model.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {
    val userDataFlow: Flow<User?>
    suspend fun updateUserData(firstName: String, lastName: String, email: String)
}
