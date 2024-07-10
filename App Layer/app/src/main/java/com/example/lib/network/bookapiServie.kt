package com.example.lib.network


import retrofit2.http.GET

interface  booksApiService{
    @GET("books")
    suspend fun getBooks(): List<bookData>


}