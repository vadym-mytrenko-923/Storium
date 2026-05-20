package com.storium.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import kotlinx.cinterop.ExperimentalForeignApi
import platform.Foundation.NSDocumentDirectory
import platform.Foundation.NSFileManager
import platform.Foundation.NSUserDomainMask

fun createUserDataStore(): DataStore<Preferences> = createDataStore(path = getDocumentFile(USER_STORAGE_NAME))

@OptIn(ExperimentalForeignApi::class)
private fun getDocumentFile(fileName: String): String {
    val documentsUrl = requireNotNull(
        NSFileManager.defaultManager.URLForDirectory(
            directory = NSDocumentDirectory,
            inDomain = NSUserDomainMask,
            appropriateForURL = null,
            create = false,
            error = null,
        )
    ) {
        "NSDocumentDirectory not found"
    }

    return requireNotNull(
        documentsUrl.URLByAppendingPathComponent(fileName)?.path
    ) {
        "Failed to construct path for $fileName"
    }
}
