package com.example.lib.data

import com.example.lib.network.LoginRequest
import com.example.lib.network.LoginResponse
import com.example.lib.network.bookData
import com.example.lib.network.booksApiService

import retrofit2.Response


interface BookRepo{
    suspend fun getBookdata():List<bookData>
    suspend fun getBookByID(bookID:Int):bookData
    suspend fun searchBooks(bookName: String): List<bookData>
    suspend fun login(username: String, password: String):Response<LoginResponse>

}
class NetworkbookRepo(
    private val boookApiService: booksApiService
):BookRepo{
    override suspend fun getBookdata():List<bookData> = boookApiService.getBooks()
    override suspend fun getBookByID(bookID: Int): bookData {
        return boookApiService.getBookById(bookID)
    }
    override suspend fun searchBooks(bookName: String): List<bookData> {
        return boookApiService.searchBooks(bookName)
    }

    override suspend fun login(username: String, password: String): Response<LoginResponse> {
        return boookApiService.login(LoginRequest(username,password))
    }

}