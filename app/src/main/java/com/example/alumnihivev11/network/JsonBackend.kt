package com.example.alumnihivev11.network

import kotlinx.serialization.json.Json

val JsonBackend = Json {
    ignoreUnknownKeys = true
    coerceInputValues = true
    isLenient = true
    encodeDefaults = true
}
