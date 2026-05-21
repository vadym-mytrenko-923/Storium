package com.storium.data.local.storage

import android.content.Context
import com.google.crypto.tink.Aead
import com.google.crypto.tink.KeysetHandle
import com.google.crypto.tink.RegistryConfiguration
import com.google.crypto.tink.aead.AeadConfig
import com.google.crypto.tink.aead.AesGcmKeyManager
import com.google.crypto.tink.integration.android.AndroidKeysetManager

private const val MASTER_KEY_ALIAS = "ds_master_key"
private const val KEYSET_PREF_FILE = "ds_keyset_prefs"
private const val KEYSET_NAME = "ds_keyset"

fun createAeadEncryption(context: Context): Aead {
    AeadConfig.register()
    val keysetHandle: KeysetHandle = AndroidKeysetManager.Builder()
        .withSharedPref(context, KEYSET_NAME, KEYSET_PREF_FILE)
        .withKeyTemplate(AesGcmKeyManager.aes256GcmTemplate())
        .withMasterKeyUri("android-keystore://$MASTER_KEY_ALIAS")
        .build()
        .keysetHandle

    return keysetHandle.getPrimitive(RegistryConfiguration.get(), Aead::class.java)
}
