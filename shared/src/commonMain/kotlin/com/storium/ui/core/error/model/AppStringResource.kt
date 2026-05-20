package com.storium.ui.core.error.model

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

data class AppStringResource(
    val res: StringResource,
    val args: List<Any> = emptyList(),
) {
    @Composable
    fun getString(): String = stringResource(res, *args.toTypedArray())
}
