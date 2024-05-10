package com.example.hilt_tutorial.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDao {
    @Insert
    fun insertUser(user: UserEntity)

    @Query("SELECT * FROM Users")
    fun getAllUsers(): List<UserEntity>

    @Query("SELECT * FROM Users WHERE id = :userId")
    fun getUserById(userId: Int): Flow<UserEntity>

}