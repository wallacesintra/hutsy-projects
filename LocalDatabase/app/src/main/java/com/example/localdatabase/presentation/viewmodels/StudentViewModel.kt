package com.example.localdatabase.presentation.viewmodels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.localdatabase.data.local.Student
import com.example.localdatabase.data.local.StudentDao
import com.example.localdatabase.presentation.models.Students
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class StudentViewModel(
    private val studentDao: StudentDao
) : ViewModel(){
    private val _state = MutableStateFlow(Students())

    var name:  String by mutableStateOf("")
    var id: String by mutableStateOf("")

    private val _students = _state
        .flatMapLatest { data ->
            studentDao.displayAllStudents()
        }
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())

    val state = combine(_state, _students) { state, students ->
        state.copy(
            studentList = students
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), Students())

    private fun addStudent(name: String, id: String){
        viewModelScope.launch(Dispatchers.IO){
            val student: Student = Student(
                name = name,
                id = id
            )
            studentDao.insertToDB(student)
        }
    }

    fun updateName(value: String){
        name = value
    }
    fun updateId(value: String){
        id = value
    }


}