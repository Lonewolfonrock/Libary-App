package com.example.lib.data

import com.example.lib.network.AuthInterceptor
import com.example.lib.network.TokenProvider
import com.example.lib.network.booksApiService
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer{

    val bookDataRepo: BookRepo;
}

class DefaultAppContainer(private val tokenProvider: TokenProvider):AppContainer{
    private val Base_URL ="http://192.168.1.71:3000/"

    private val okHttpClient:OkHttpClient by lazy {
        OkHttpClient.Builder()
            .addInterceptor(AuthInterceptor(tokenProvider))
            .build()
    }

    private val retrofit:Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(Base_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }
//    private val retrofit:Retrofit = Retrofit.Builder().baseUrl(Base_URL)
//        .addConverterFactory(GsonConverterFactory.create()).build()
//
    private val retrofitService: booksApiService by lazy {
        retrofit.create(booksApiService::class.java)
    }
    override val bookDataRepo: BookRepo by lazy {
        NetworkbookRepo(retrofitService)
    }
}