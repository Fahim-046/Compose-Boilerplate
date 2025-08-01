package com.fahimdev.composeboilerplate.core.network

import com.fahimdev.composeboilerplate.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        private const val TOKEN = "YOUR_TOKEN"
        private fun buildClient(): OkHttpClient {
            val builder = OkHttpClient.Builder()

            if (BuildConfig.DEBUG) {
                builder.addInterceptor(
                    HttpLoggingInterceptor().apply {
                        level = HttpLoggingInterceptor.Level.BODY
                    }
                )
            }

            builder.addInterceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Authorization", "Bearer $TOKEN")
                    .build()
                chain.proceed(request)
            }

            return builder.build()
        }

        fun retrofitInstance(gsonConverterFactory: GsonConverterFactory): Retrofit {
            return Retrofit.Builder()
                .client(buildClient())
                .baseUrl("https://api.example.com/")
                .addConverterFactory(gsonConverterFactory)
                .build()
        }
    }
}