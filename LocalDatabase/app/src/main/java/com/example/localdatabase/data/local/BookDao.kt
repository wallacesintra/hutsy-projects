package com.example.localdatabase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface BookDao {
    @Insert
    fun insertToBookDB(book: Book)

    @Delete
    fun deleteBook(book: Book)

    @Query("SELECT * FROM student")
    fun displayAllBooks(): Flow<List<Book>>

}