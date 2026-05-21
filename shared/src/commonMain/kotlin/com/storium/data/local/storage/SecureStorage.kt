package com.storium.data.local.storage

import kotlinx.coroutines.flow.Flow

interface SecureStorage {
    fun getFlowValue(key: String): Flow<String?>
    suspend fun putValue(key: String, value: String)
    suspend fun getValue(key: String): String?
    suspend fun remove(key: String)
    suspend fun clear()
}
