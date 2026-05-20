package com.storium.ui.core.error.model

import androidx.compose.runtime.Composable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

sealed interface UiText {

    data class Raw(val value: String) : UiText

    data class Resource(
        val res: StringResource,
        val args: List<Any> = emptyList(),
    ) : UiText

    @Composable
    fun asString(): String = when (this) {
        is Raw -> value
        is Resource -> stringResource(res, *args.toTypedArray())
    }
}
