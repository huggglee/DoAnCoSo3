package com.example.dacs3

import android.app.Application

class MyApp:Application() {
    lateinit var container:AppContainer

    override fun onCreate() {
        super.onCreate()
        container=AppDataContainer(this)
    }
}