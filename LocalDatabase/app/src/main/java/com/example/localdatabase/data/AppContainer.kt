package com.example.localdatabase.data

import android.content.Context
import androidx.room.Room
import com.example.localdatabase.data.local.AppDatabase

interface AppContainer {
    val localDB: AppDatabase

}

class DefaultContainer(private val applicationContext: Context) : AppContainer{
    override val localDB: AppDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "database.db"
        ).build()
    }

}