package com.example.localdatabase

import android.app.Application
import com.example.localdatabase.data.AppContainer
import com.example.localdatabase.data.DefaultContainer

class LocalDatabaseApplication: Application(){
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = DefaultContainer(this)
    }
}