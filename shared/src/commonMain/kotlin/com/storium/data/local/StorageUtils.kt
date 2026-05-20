package com.storium.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

// TODO: Add encryption layer (expect/actual with Tink on Android, Keychain on iOS)

suspend fun <T> DataStore<Preferences>.putValue(key: Preferences.Key<T>, value: T) {
    edit { it[key] = value }
}

fun <T> DataStore<Preferences>.getFlowValue(key: Preferences.Key<T>, default: T): Flow<T> =
    data.map { it[key] ?: default }

suspend fun <T> DataStore<Preferences>.getValueOnce(key: Preferences.Key<T>): T? =
    data.firstOrNull()?.get(key)

suspend inline fun <reified T> DataStore<Preferences>.putJsonValue(
    key: Preferences.Key<String>,
    value: T,
) {
    putValue(key, Json.encodeToString(value))
}

suspend inline fun <reified T> DataStore<Preferences>.getJsonValue(
    key: Preferences.Key<String>,
): T? {
    val json = getValueOnce(key) ?: return null
    return Json.decodeFromString(json)
}

fun <T> DataStore<Preferences>.getFlowJsonValue(
    key: Preferences.Key<String>,
    deserialize: (String) -> T,
): Flow<T?> = data.map { prefs ->
    prefs[key]?.let { deserialize(it) }
}

suspend fun DataStore<Preferences>.clearAll() {
    edit { it.clear() }
}
