package com.storium.ui.core.error

import com.storium.ui.core.error.model.AppStringResource
import com.storium.ui.core.error.model.UiError
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.ServerResponseException
import storium.shared.generated.resources.Res
import storium.shared.generated.resources.errorLoginFailed
import storium.shared.generated.resources.errorNetwork
import storium.shared.generated.resources.errorServer
import storium.shared.generated.resources.errorUnknown

class UiErrorParser {
    fun parseError(error: Throwable): UiError = when (error) {
        is ClientRequestException -> UiError(messageResource = AppStringResource(Res.string.errorLoginFailed))
        is ServerResponseException -> UiError(messageResource = AppStringResource(Res.string.errorServer))
        is kotlinx.io.IOException -> UiError(messageResource = AppStringResource(Res.string.errorNetwork))
        else -> UiError(messageResource = AppStringResource(Res.string.errorUnknown))
    }
}
