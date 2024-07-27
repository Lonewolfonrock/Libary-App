package com.example.lib.data

import com.example.lib.network.LoginRequest
import com.example.lib.network.TokenProvider
import com.example.lib.network.booksApiService

class AuthRepo(private val apiService: booksApiService, private val tokenProvider: TokenProvider) {

    suspend fun login(username: String, password: String): Boolean {
        val response = apiService.login(LoginRequest(username, password))
        return if (response.isSuccessful) {
            response.body()?.let {
                tokenProvider.setToken(it.token)
                true
            } ?: false
        } else {
            false
        }
    }
}
