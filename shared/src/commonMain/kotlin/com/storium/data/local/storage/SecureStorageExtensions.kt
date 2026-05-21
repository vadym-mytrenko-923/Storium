package com.storium.data.local.storage

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json

suspend inline fun <reified T> SecureStorage.putJsonValue(key: String, json: Json, value: T) = putValue(
    key = key,
    value = json.encodeToString(value)
)

suspend inline fun <reified T> SecureStorage.getJsonValue(key: String, json: Json): T? = getValue(key)?.let {
    json.decodeFromString<T>(it)
}

inline fun <reified T> SecureStorage.getJsonFlowValue(key: String, json: Json): Flow<T?> = getFlowValue(key).map {
    it?.let { json.decodeFromString<T>(it) }
}
