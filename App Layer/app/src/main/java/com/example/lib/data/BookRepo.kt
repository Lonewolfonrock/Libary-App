package com.example.lib.data

import com.example.lib.network.bookData
import com.example.lib.network.booksApiService

interface BookRepo{
    suspend fun getBookdata():List<bookData>
    suspend fun getBookByID(bookID:Int):bookData
}
class NetworkbookRepo(
    private val boookApiService: booksApiService
):BookRepo{
    override suspend fun getBookdata():List<bookData> = boookApiService.getBooks()
    override suspend fun getBookByID(bookID: Int): bookData {
        return boookApiService.getBookById(bookID)
    }
}