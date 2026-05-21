package com.storium.ui.core.error.model

import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.getString

data class AppStringResource(
    val res: StringResource,
    val args: List<Any> = emptyList(),
) {
    suspend fun getString(): String = getString(res, *args.toTypedArray())
}
