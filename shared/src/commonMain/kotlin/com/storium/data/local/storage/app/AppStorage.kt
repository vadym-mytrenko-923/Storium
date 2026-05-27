package com.storium.data.local.storage.app

interface AppStorage {
    suspend fun getLastProductsSyncTimestamp(): Long?
    suspend fun setProductsSynced()
}
