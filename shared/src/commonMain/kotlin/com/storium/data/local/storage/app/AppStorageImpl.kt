package com.storium.data.local.storage.app

import com.storium.data.local.storage.SecureStorage
import com.storium.util.time.currentTimeMillis

private const val KEY_PRODUCTS_LAST_SYNC = "products_last_sync_at"

class AppStorageImpl(private val storage: SecureStorage) : AppStorage {
    override suspend fun getLastProductsSyncTimestamp(): Long? {
        return storage.getValue(KEY_PRODUCTS_LAST_SYNC)?.toLongOrNull()
    }

    override suspend fun setProductsSynced() {
        storage.putValue(KEY_PRODUCTS_LAST_SYNC, currentTimeMillis().toString())
    }
}
