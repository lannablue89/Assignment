package com.nab.assignment.test_util

import okhttp3.mockwebserver.MockResponse
import okio.buffer
import okio.source

fun MockResponse.setBodyFromFile(fileName: String): MockResponse {
    val inputStream = javaClass.classLoader!!.getResourceAsStream("api-response/$fileName")
    val source = inputStream.source().buffer()
    val body = source.readString(Charsets.UTF_8)
    return setBody(body)
}