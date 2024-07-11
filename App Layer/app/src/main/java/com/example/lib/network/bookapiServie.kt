package com.example.lib.network


import retrofit2.http.GET
import retrofit2.http.Path

interface  booksApiService{
    @GET("books")
    suspend fun getBooks(): List<bookData>
    @GET("book/{id}")
    suspend fun getBookById(@Path("id") bookId: Int): bookData



}