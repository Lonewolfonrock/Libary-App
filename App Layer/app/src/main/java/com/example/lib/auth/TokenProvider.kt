package com.example.lib.auth

import android.content.Context
import android.content.SharedPreferences

interface TokenProvider {
    fun saveToken(token: String)
    fun getToken(): String?
    fun clearToken()
}

class SharedPreferencesTokenProvider(context: Context) : TokenProvider {

    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)

    companion object {
        private const val KEY_TOKEN = "key_token"
    }

    override fun saveToken(token: String) {
        sharedPreferences.edit().putString(KEY_TOKEN, token).apply()
    }

    override fun getToken(): String? {
        return sharedPreferences.getString(KEY_TOKEN, null)
    }

    override fun clearToken() {
        sharedPreferences.edit().remove(KEY_TOKEN).apply()
    }
}
