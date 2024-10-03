package com.example.kmp_ktor

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform