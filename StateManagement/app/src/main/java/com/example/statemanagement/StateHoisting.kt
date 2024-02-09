package com.example.statemanagement

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

@Composable
fun SayHello(
//    State Hoisting
    name: String, //value
    onNameChange: (String) -> Unit //onValueChange
){
    Column {
        Text(text = "Hello, $name")
        OutlinedTextField(
            value = name,
            label = {
                          Text(text = "Name")
            },
            onValueChange = onNameChange
        )
    }
}

@Composable
fun HelloScreen(){
    var name by rememberSaveable {
        mutableStateOf("")
    }

    SayHello(
        name = name,
        onNameChange = { name = it }
    )
}