package com.example.weather.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [LocationEntity::class],
    version = 1
)
abstract class LocationDatabase: RoomDatabase() {
    abstract val dao: LocationDao
}