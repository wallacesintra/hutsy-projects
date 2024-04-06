package com.example.localdatabase.data.local

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface StudentDao {
    @Insert
    fun insertToDB(student: Student)

    @Delete
    fun deleteStudent(student: Student)

    @Query("SELECT * FROM student")
    fun displayAllStudents(): Flow<List<Student>>

}