package com.example.localdatabase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.localdatabase.presentation.screens.StudentScreen
import com.example.localdatabase.presentation.viewmodels.StudentViewModel
import com.example.localdatabase.ui.theme.LocalDatabaseTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LocalDatabaseTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: StudentViewModel = viewModel(factory = StudentViewModel.Factory)
                    val name = viewModel.name
                    val id = viewModel.id
                    val state by viewModel.state.collectAsState()

                    StudentScreen(
                        name = name,
                        id = id,
                        onNameChange = { viewModel.updateName(it) },
                        onIdChange = { viewModel.updateId(it) },
                        addStudent = {viewModel.addStudent(name = name, id = id)},
                        list = state.studentList
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    LocalDatabaseTheme {
        Greeting("Android")
    }
}