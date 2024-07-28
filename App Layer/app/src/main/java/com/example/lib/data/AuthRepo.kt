package com.example.lib.data

import android.util.Log
import com.example.lib.auth.JwtRequest
import com.example.lib.auth.JwtResponse
import com.example.lib.auth.TokenProvider
import com.example.lib.auth.User
import com.example.lib.network.booksApiService
class AuthRepo(private val authService: booksApiService, private val tokenProvider: TokenProvider) {

    suspend fun login(email: String, password: String): Result<JwtResponse> {
        return try {
            val request = JwtRequest(email, password)
            Log.d("AuthRepo", "Sending login request: $request")
            val response = authService.login(request)
            if (response.isSuccessful) {
                response.body()?.let {
                    Log.d("AuthRepo", "Login successful: $it")
                    tokenProvider.saveToken(it.jwtToken)
                    Result.success(it)
                } ?: Result.failure(Exception("No token found"))
            } else {
                val errorBody = response.errorBody()?.string() ?: "Unknown error"
                Log.e("AuthRepo", "Login failed: $errorBody (Code: ${response.code()})")
                Result.failure(Exception("Login failed: $errorBody"))
            }
        } catch (e: Exception) {
            Log.e("AuthRepo", "Login error: ${e.message}")
            Result.failure(e)
        }
    }

    suspend fun signup(user: User): Result<User> {
        return try {
            val response = authService.signup(user)
            if (response.isSuccessful) {
                response.body()?.let {
                    Result.success(it)
                } ?: Result.failure(Exception("Signup failed"))
            } else {
                Result.failure(Exception("Signup failed"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}