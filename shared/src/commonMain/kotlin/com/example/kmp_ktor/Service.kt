package com.example.kmp_ktor

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

class Service {
    private val client = HttpClient()

    suspend fun getDoc(): String {
        val response = client.get("https://ktor.io/docs/")
        return response.bodyAsText()
    }
}