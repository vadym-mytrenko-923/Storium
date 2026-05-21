@file:OptIn(ExperimentalForeignApi::class, BetaInteropApi::class)

package com.storium.data.local.storage

import kotlinx.cinterop.BetaInteropApi
import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.alloc
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.ptr
import kotlinx.cinterop.value
import platform.CoreFoundation.CFDictionaryAddValue
import platform.CoreFoundation.CFDictionaryCreateMutable
import platform.CoreFoundation.CFTypeRefVar
import platform.CoreFoundation.kCFAllocatorDefault
import platform.CoreFoundation.kCFBooleanTrue
import platform.Foundation.CFBridgingRelease
import platform.Foundation.CFBridgingRetain
import platform.Foundation.NSData
import platform.Foundation.NSString
import platform.Foundation.NSUTF8StringEncoding
import platform.Foundation.create
import platform.Foundation.dataUsingEncoding
import platform.Security.SecItemAdd
import platform.Security.SecItemCopyMatching
import platform.Security.SecItemDelete
import platform.Security.SecItemUpdate
import platform.Security.errSecSuccess
import platform.Security.kSecAttrAccessible
import platform.Security.kSecAttrAccessibleWhenUnlockedThisDeviceOnly
import platform.Security.kSecAttrAccount
import platform.Security.kSecAttrService
import platform.Security.kSecClass
import platform.Security.kSecClassGenericPassword
import platform.Security.kSecMatchLimit
import platform.Security.kSecMatchLimitOne
import platform.Security.kSecReturnData
import platform.Security.kSecValueData

private const val KEYCHAIN_SERVICE_PREFIX = "com.storium"

interface KeychainProvider {
    fun get(key: String): String?
    fun set(key: String, value: String)
    fun delete(key: String)
    fun deleteAll()
}

class KeychainProviderImpl(name: String) : KeychainProvider {
    private val serviceName = "$KEYCHAIN_SERVICE_PREFIX.$name"

    override fun get(key: String): String? = memScoped {
        val query = CFDictionaryCreateMutable(kCFAllocatorDefault, 6, null, null) ?: return null
        CFDictionaryAddValue(query, kSecClass, kSecClassGenericPassword)
        CFDictionaryAddValue(query, kSecAttrService, CFBridgingRetain(serviceName))
        CFDictionaryAddValue(query, kSecAttrAccount, CFBridgingRetain(key))
        CFDictionaryAddValue(query, kSecReturnData, kCFBooleanTrue)
        CFDictionaryAddValue(query, kSecMatchLimit, kSecMatchLimitOne)

        val result = alloc<CFTypeRefVar>()
        if (SecItemCopyMatching(query, result.ptr) != errSecSuccess) return null

        val data = CFBridgingRelease(result.value) as? NSData ?: return null
        NSString.create(data = data, encoding = NSUTF8StringEncoding)?.toString()
    }

    override fun set(key: String, value: String) {
        val valueData = NSString.create(string = value).dataUsingEncoding(NSUTF8StringEncoding) ?: return

        // Try to update the data first
        val lookupQuery = CFDictionaryCreateMutable(kCFAllocatorDefault, 3, null, null) ?: return
        CFDictionaryAddValue(lookupQuery, kSecClass, kSecClassGenericPassword)
        CFDictionaryAddValue(lookupQuery, kSecAttrService, CFBridgingRetain(serviceName))
        CFDictionaryAddValue(lookupQuery, kSecAttrAccount, CFBridgingRetain(key))

        val updateAttrs = CFDictionaryCreateMutable(kCFAllocatorDefault, 1, null, null) ?: return
        CFDictionaryAddValue(updateAttrs, kSecValueData, CFBridgingRetain(valueData))

        if (SecItemUpdate(lookupQuery, updateAttrs) != errSecSuccess) {
            val addQuery = CFDictionaryCreateMutable(kCFAllocatorDefault, 5, null, null)
            CFDictionaryAddValue(addQuery, kSecClass, kSecClassGenericPassword)
            CFDictionaryAddValue(addQuery, kSecAttrService, CFBridgingRetain(serviceName))
            CFDictionaryAddValue(addQuery, kSecAttrAccount, CFBridgingRetain(key))
            CFDictionaryAddValue(addQuery, kSecAttrAccessible, kSecAttrAccessibleWhenUnlockedThisDeviceOnly)
            CFDictionaryAddValue(addQuery, kSecValueData, CFBridgingRetain(valueData))

            addQuery?.let { SecItemAdd(it, null) }
        }
    }

    override fun delete(key: String) {
        val query = CFDictionaryCreateMutable(kCFAllocatorDefault, 3, null, null) ?: return
        CFDictionaryAddValue(query, kSecClass, kSecClassGenericPassword)
        CFDictionaryAddValue(query, kSecAttrService, CFBridgingRetain(serviceName))
        CFDictionaryAddValue(query, kSecAttrAccount, CFBridgingRetain(key))
        SecItemDelete(query)
    }

    override fun deleteAll() {
        val query = CFDictionaryCreateMutable(kCFAllocatorDefault, 2, null, null) ?: return
        CFDictionaryAddValue(query, kSecClass, kSecClassGenericPassword)
        CFDictionaryAddValue(query, kSecAttrService, CFBridgingRetain(serviceName))
        SecItemDelete(query)
    }
}
