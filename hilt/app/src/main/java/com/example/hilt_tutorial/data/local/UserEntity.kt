package com.example.hilt_tutorial.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Users")
data class UserEntity(
    @PrimaryKey val id: Int? = null,
    val userName: String,
    val details: String,
)