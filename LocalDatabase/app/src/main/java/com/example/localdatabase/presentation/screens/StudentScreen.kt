package com.example.localdatabase.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.localdatabase.data.local.Student

@Composable
fun StudentScreen(
    name: String,
    id: String,
    onNameChange: (String) -> Unit,
    onIdChange: (String) -> Unit,
    addStudent: () -> Unit,
    list: List<Student>
){
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Column {
            TextField(value = name, onValueChange = { onNameChange(it) })
            TextField(value = id , onValueChange = { onIdChange(it) })
            Button(onClick = { addStudent()}) {
                Text(text = "add student")
            }

            LazyColumn {
                items(list) {studentItem ->
                    StudentListItem(student = studentItem)
                }
            }
        }

    }

}

@Composable
fun StudentListItem(student: Student) {
    Column {
        Row {
            Text(text = student.name)
        }
        Row {
            Text(text = student.id)
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentScreenPreview(){
    StudentScreen(name = "John", id = "089", onNameChange = {}, onIdChange = {}, {},list = emptyList())
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentListItemPreview(){
    StudentListItem(student = Student("89", "john doe"))
}