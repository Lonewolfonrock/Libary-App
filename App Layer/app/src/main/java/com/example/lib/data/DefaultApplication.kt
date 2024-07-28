package com.example.lib.data

import android.content.Context
import com.example.lib.auth.AuthInterceptor
import com.example.lib.auth.SharedPreferencesTokenProvider
import com.example.lib.auth.TokenProvider
import com.example.lib.network.booksApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer {
    val bookDataRepo: BookRepo
    val authRepo: AuthRepo
}

class DefaultAppContainer(context: Context) : AppContainer {

    private val BASE_URL = "http://192.168.1.64:3000/"

    private val logging = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    private val tokenProvider: TokenProvider = SharedPreferencesTokenProvider(context)

    private val authInterceptor = AuthInterceptor(tokenProvider)

    private val httpClient = OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(authInterceptor)
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .build()

    private val retrofitService: booksApiService by lazy {
        retrofit.create(booksApiService::class.java)
    }

    override val bookDataRepo: BookRepo by lazy {
        NetworkbookRepo(retrofitService)
    }

    override val authRepo: AuthRepo by lazy {
        AuthRepo(retrofitService, tokenProvider)
    }
}
