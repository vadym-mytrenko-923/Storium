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
import platform.CoreFoundation.CFMutableDictionaryRef
import platform.CoreFoundation.CFTypeRefVar
import platform.CoreFoundation.kCFAllocatorDefault
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

interface KeychainProvider {
    fun get(key: String): String?
    fun set(key: String, value: String)
    fun delete(key: String)
    fun deleteAll()
}

class KeychainProviderImpl(private val serviceName: String) : KeychainProvider {

    override fun get(key: String): String? = memScoped {
        val query = itemQuery(key) ?: return null
        CFDictionaryAddValue(query, kSecReturnData, CFBridgingRetain(true as Any))
        CFDictionaryAddValue(query, kSecMatchLimit, kSecMatchLimitOne)

        val result = alloc<CFTypeRefVar>()
        if (SecItemCopyMatching(query, result.ptr) != errSecSuccess) return null

        val data = CFBridgingRelease(result.value) as? NSData ?: return null
        NSString.create(data = data, encoding = NSUTF8StringEncoding)?.toString()
    }

    override fun set(key: String, value: String) {
        val valueData = value.toNSData() ?: return
        val query = itemQuery(key) ?: return
        val attributes = valueAttributes(valueData) ?: return

        val status = SecItemUpdate(query, attributes)
        if (status != errSecSuccess) {
            CFDictionaryAddValue(query, kSecValueData, CFBridgingRetain(valueData))
            SecItemAdd(query, null)
        }
    }

    override fun delete(key: String) {
        val query = itemQuery(key) ?: return
        SecItemDelete(query)
    }

    override fun deleteAll() {
        val query = serviceQuery() ?: return
        SecItemDelete(query)
    }

    private fun serviceQuery(): CFMutableDictionaryRef? =
        CFDictionaryCreateMutable(kCFAllocatorDefault, 2, null, null)?.apply {
            CFDictionaryAddValue(this, kSecClass, kSecClassGenericPassword)
            CFDictionaryAddValue(this, kSecAttrService, CFBridgingRetain(serviceName as Any))
        }

    private fun itemQuery(key: String): CFMutableDictionaryRef? =
        serviceQuery()?.apply {
            CFDictionaryAddValue(this, kSecAttrAccount, CFBridgingRetain(key as Any))
            CFDictionaryAddValue(this, kSecAttrAccessible, kSecAttrAccessibleWhenUnlockedThisDeviceOnly)
        }

    private fun valueAttributes(data: NSData): CFMutableDictionaryRef? =
        CFDictionaryCreateMutable(kCFAllocatorDefault, 1, null, null)?.apply {
            CFDictionaryAddValue(this, kSecValueData, CFBridgingRetain(data))
        }

    private fun String.toNSData(): NSData? = NSString.create(string = this).dataUsingEncoding(NSUTF8StringEncoding)
}
