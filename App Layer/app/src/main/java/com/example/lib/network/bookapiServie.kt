package com.example.lib.network

import com.example.lib.auth.JwtRequest
import com.example.lib.auth.JwtResponse
import com.example.lib.auth.User
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface booksApiService {
    @GET("api/books")
    suspend fun getBooks(): List<BookData>

    @GET("api/books/{id}")
    suspend fun getBookById(@Path("id") bookId: Int): BookData

    @GET("api/books/search/{bookname}")
    suspend fun searchBooks(@Path("bookname") bookName: String): List<BookData>

    @POST("auth/login")
    suspend fun login(@Body request: JwtRequest): Response<JwtResponse>

    @POST("auth/create-user")
    suspend fun signup(@Body user: User): Response<User>

}

