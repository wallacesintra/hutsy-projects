package com.example.localdatabase.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Book(
    @PrimaryKey val id : String,
    val title: String,
    val author: String,
)