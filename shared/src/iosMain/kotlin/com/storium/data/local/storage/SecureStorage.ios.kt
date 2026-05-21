package com.storium.data.local.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

class KeychainSecureStorage(private val keychain: KeychainProvider) : SecureStorage {
    private val flows = mutableMapOf<String, MutableStateFlow<String?>>()

    override fun getFlowValue(key: String): Flow<String?> = getOrCreateFlow(key)

    override suspend fun putValue(key: String, value: String) {
        keychain.set(key, value)
        getOrCreateFlow(key).value = value
    }

    override suspend fun getValue(key: String): String? = keychain.get(key)

    override suspend fun remove(key: String) {
        keychain.delete(key)
        flows[key]?.value = null
    }

    override suspend fun clear() {
        keychain.deleteAll()
        flows.values.forEach { it.value = null }
    }

    private fun getOrCreateFlow(key: String): MutableStateFlow<String?> = flows.getOrPut(key) {
        MutableStateFlow(keychain.get(key))
    }
}
