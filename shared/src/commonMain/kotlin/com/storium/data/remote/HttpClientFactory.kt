package com.storium.data.remote

import com.storium.data.remote.util.HttpLogger
import com.storium.domain.system.logger.AppLogger
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

fun createHttpClient(json: Json, appLogger: AppLogger): HttpClient = HttpClient {
    expectSuccess = true
    defaultRequest {
        url(ApiConstants.BASE_URL)
    }
    install(ContentNegotiation) {
        json(json)
    }
    install(Logging) {
        logger = HttpLogger(appLogger)
        level = LogLevel.BODY
    }
}
