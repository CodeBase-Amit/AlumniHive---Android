package com.example.alumnihivev11.network

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking

object BackendClient {

    fun create(sessionManager: SessionManager): BackendApi {
        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val authInterceptor = Interceptor { chain ->
            val original = chain.request()
            val token = runBlocking { sessionManager.tokenFlow.firstOrNull() }
            val request = if (!token.isNullOrBlank()) {
                original.newBuilder()
                    .addHeader("Authorization", "Bearer $token")
                    .build()
            } else {
                original
            }
            chain.proceed(request)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .addInterceptor(logging)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BackendConfig.DEFAULT_BASE_URL)
            .client(client)
            .addConverterFactory(JsonBackend.asConverterFactory("application/json".toMediaType()))
            .build()

        return retrofit.create(BackendApi::class.java)
    }
}
