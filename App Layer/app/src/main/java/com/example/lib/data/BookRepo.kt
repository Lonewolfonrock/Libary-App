package com.example.lib.data


import com.example.lib.auth.JwtRequest
import com.example.lib.auth.JwtResponse
import com.example.lib.auth.User
import com.example.lib.network.BookData
import com.example.lib.network.booksApiService
import okhttp3.Request

import retrofit2.Response


interface BookRepo{
    suspend fun getBookdata():List<BookData>
    suspend fun getBookByID(bookID:Int):BookData
    suspend fun searchBooks(bookName: String): List<BookData>

}
class NetworkbookRepo(
    private val boookApiService: booksApiService
):BookRepo{
    override suspend fun getBookdata():List<BookData> = boookApiService.getBooks()
    override suspend fun getBookByID(bookID: Int): BookData {
        return boookApiService.getBookById(bookID)
    }
    override suspend fun searchBooks(bookName: String): List<BookData> {
        return boookApiService.searchBooks(bookName)
    }


}