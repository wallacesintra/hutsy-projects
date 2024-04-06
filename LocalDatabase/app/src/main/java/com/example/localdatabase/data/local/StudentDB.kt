package com.example.localdatabase.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [Student::class, Book::class],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract val studentDao: StudentDao
    abstract val bookDao: BookDao
}