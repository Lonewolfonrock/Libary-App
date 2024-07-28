package com.example.lib.them


import android.app.Application
import com.example.lib.data.AppContainer
import com.example.lib.data.DefaultAppContainer

class BookDataApplication:Application() {

    lateinit var container: AppContainer
    override fun onCreate(){
        super.onCreate()
        container = DefaultAppContainer(this)

    }



}