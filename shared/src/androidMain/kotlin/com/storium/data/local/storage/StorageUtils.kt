package com.storium.data.local.storage

import android.util.Base64
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.google.crypto.tink.Aead
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

private val AAD = "storage_v1".toByteArray()

private fun Aead.encryptToBase64(plainText: String): String {
    val cipher = encrypt(plainText.toByteArray(Charsets.UTF_8), AAD)
    return Base64.encodeToString(cipher, Base64.NO_WRAP)
}

private fun Aead.decryptOrNull(base64Cipher: String): String? = runCatching {
    val cipherBytes = Base64.decode(base64Cipher, Base64.NO_WRAP)
    String(decrypt(cipherBytes, AAD), Charsets.UTF_8)
}.getOrNull()

suspend fun DataStore<Preferences>.putValue(key: Preferences.Key<String>, encryption: Aead, value: String) {
    edit { it[key] = encryption.encryptToBase64(value) }
}

fun DataStore<Preferences>.getFlowValue(key: Preferences.Key<String>, encryption: Aead): Flow<String?> =
    data.map { it[key]?.let { cipher -> encryption.decryptOrNull(cipher) } }

suspend fun DataStore<Preferences>.getValueOnce(key: Preferences.Key<String>, encryption: Aead): String? {
    val prefs = data.firstOrNull() ?: return null
    val cipher = prefs[key] ?: return null
    return encryption.decryptOrNull(cipher)
}

suspend inline fun <reified T> DataStore<Preferences>.putJsonValue(
    key: Preferences.Key<String>,
    encryption: Aead,
    value: T,
) {
    putValue(key, encryption, Json.encodeToString(value))
}

suspend inline fun <reified T> DataStore<Preferences>.getJsonValue(
    key: Preferences.Key<String>,
    encryption: Aead,
): T? {
    val json = getValueOnce(key, encryption) ?: return null
    return Json.decodeFromString(json)
}

suspend fun <T> DataStore<Preferences>.removeValue(key: Preferences.Key<T>) {
    edit { it.remove(key) }
}

suspend fun DataStore<Preferences>.clearAll() {
    edit { it.clear() }
}
