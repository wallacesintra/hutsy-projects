package com.example.calculator.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.calculator.AllButtons
import com.example.calculator.ui.components.ButtonsLayout
import com.example.calculator.ui.components.Output

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier
){
    Column {
        Output(
            operation = "56 + 67",
            result = 87979,
            modifier = Modifier
                .height(200.dp)

        )
//        Spacer(modifier = Modifier.weight(1f))
        ButtonsLayout(buttons = AllButtons)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview(){
    CalculatorScreen(modifier = Modifier)
}

