package com.example.hutsypractices

import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MailOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import com.example.hutsypractices.ui.theme.HutsyPracticesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            HutsyPracticesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    var text by remember {
        mutableStateOf("")
    }
    Column {
        Text(
            text = "Hello $name!",
            modifier = modifier
        )

//        TextField(
//            leadingIcon = {
//                Icon(imageVector = Icons.Default.MailOutline, contentDescription = null)
//            },
//            supportingText = {
//                             Text(text = "read")
//            },
//            isError = true,
//            prefix = {
//                     Text("Rad")
//            },
////            visualTransformation = PasswordVisualTransformation(),
//            value = text,
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
////            placeholder = {
////                          Text(text = "Password")
////            },
//            onValueChange = {
//                            text = it
//            },
////            trailingIcon = {
////                           Icon(imageVector = Icons.Default.MailOutline, contentDescription = null)
////            },
//            label = {
//                    Text(text = "Password")
//            },
//            textStyle = MaterialTheme.typography.titleLarge,
//            keyboardActions = KeyboardActions(
//                onDone =  {}
//            )
//        )

//        OutlinedTextField(value = "string", onValueChange = {})
        Text(text = text)

    }

}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HutsyPracticesTheme {
        Greeting("Android")
    }
}