package com.example.weather.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface LocationDao {
    @Insert
    fun insertLocation(location: LocationEntity)

    @Delete
    fun deleteLocation(location: LocationEntity)

    @Query("SELECT * FROM locationentity ORDER BY id DESC")
    fun displayAllReverse(): Flow<List<LocationEntity>>

}