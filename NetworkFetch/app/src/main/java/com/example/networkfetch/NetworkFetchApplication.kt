package com.example.networkfetch

import android.app.Application
import com.example.networkfetch.data.AppContainer
import com.example.networkfetch.data.DefaultContainer

class NetworkFetchApplication : Application() {
    lateinit var container : AppContainer
    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer()
    }
}
