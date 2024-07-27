package com.example.lib.them

import android.app.Application
import com.example.lib.data.AppContainer
import com.example.lib.data.DefaultAppContainer
import com.example.lib.network.SharedPreferencesTokenProvider

class BookDataApplication:Application() {

    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        val tokenProvider = SharedPreferencesTokenProvider(this)
        container = DefaultAppContainer(tokenProvider)

    }



}