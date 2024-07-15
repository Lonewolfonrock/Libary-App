package com.example.lib.data

import com.example.lib.network.booksApiService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface AppContainer{

    val bookDataRepo: BookRepo;
}

class DefaultAppContainer:AppContainer{
    private val Base_URL ="http://192.168.1.71:3000/api/"
    private val retrofit:Retrofit = Retrofit.Builder().baseUrl(Base_URL)
        .addConverterFactory(GsonConverterFactory.create()).build()

    private val retrofitService: booksApiService by lazy {
        retrofit.create(booksApiService::class.java)
    }
    override val bookDataRepo: BookRepo by lazy {
        NetworkbookRepo(retrofitService)
    }


}