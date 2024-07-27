package com.example.lib.network

import android.content.Context

class SharedPreferencesTokenProvider(context:Context):TokenProvider {
    private val prefs = context.getSharedPreferences("auth_prefs", Context.MODE_PRIVATE)
    override fun getToken(): String {
        return prefs.getString("jwt_token", "") ?: ""
    }
    override fun setToken(token: String) {
        prefs.edit().putString("jwt_token", token).apply()
    }
}