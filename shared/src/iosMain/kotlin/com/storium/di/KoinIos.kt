package com.storium.di

import com.storium.di.storage.storageModule

fun initKoinIos() = initKoin(platformModules = listOf(storageModule))
