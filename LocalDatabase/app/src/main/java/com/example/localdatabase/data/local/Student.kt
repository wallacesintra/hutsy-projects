package com.example.localdatabase.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student (
    @PrimaryKey val id: String,
    val name: String,
)