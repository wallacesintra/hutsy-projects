package com.example.calculator.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Output(
    operation: String,
    result: Int,
    modifier: Modifier = Modifier
){
    var showResult by remember{
        mutableStateOf(false)
    }
    Column(
        horizontalAlignment = Alignment.End,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp)
            .clickable(
                onClick = {showResult = !showResult}
            ),
    ) {
        Text(
            text = operation,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            softWrap = true,
            style = if (showResult) MaterialTheme.typography.bodyMedium
                    else MaterialTheme.typography.titleLarge,
        )
        AnimatedVisibility(visible = showResult) {
            Text(
                text = result.toString(),
                style = MaterialTheme.typography.titleLarge,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun OutputPreview(){
    Output(operation = "34 + 6790887666655443", result = 678886868)
}