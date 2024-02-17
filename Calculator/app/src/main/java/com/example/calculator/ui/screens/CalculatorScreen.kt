package com.example.calculator.ui.screens

import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.weight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.AllButtons
import com.example.calculator.ui.components.ButtonsLayout
import com.example.calculator.ui.components.Output
import com.example.calculator.viewmodel.CalculatorScreenViewModel

@Composable
fun CalculatorScreen(
    modifier: Modifier = Modifier
){
    val viewModel = viewModel<CalculatorScreenViewModel>()
    val state = viewModel.state
    Column {
        Output(state = state)
        ButtonsLayout(buttons = AllButtons)
    }
}

@Preview(showBackground = true)
@Composable
fun CalculatorScreenPreview(){
    CalculatorScreen(modifier = Modifier)
}

