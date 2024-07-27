package com.example.lib.network

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface booksApiService {
    @GET("api/books")
    suspend fun getBooks(): List<bookData>

    @GET("api/books/{id}")
    suspend fun getBookById(@Path("id") bookId: Int): bookData

    @GET("api/books/search/{bookname}")
    suspend fun searchBooks(@Path("bookname") bookName: String): List<bookData>

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Response<LoginResponse>
}

data class LoginRequest(val username: String, val password: String)
data class LoginResponse(val sucess: Boolean,val token: String)
data class BookData(val id: Int, val title: String, val author: String)

