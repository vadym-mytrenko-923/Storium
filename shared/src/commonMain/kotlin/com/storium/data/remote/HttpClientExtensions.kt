package com.storium.data.remote

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

suspend inline fun <reified T> HttpClient.getRequest(url: String): T = get(url).body()

suspend inline fun <reified T> HttpClient.postRequest(url: String, body: Any): T = post(url) {
    contentType(ContentType.Application.Json)
    setBody(body)
}.body()
