package com.example.localdatabase.presentation.models

import com.example.localdatabase.data.local.Student

data class Students(
    val studentList: List<Student> = emptyList(),
    val name: String = "",
    val id: String = ""
)