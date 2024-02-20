package com.example.calculator.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calculator.ButtonType
import com.example.calculator.CalculatorButtons
import com.example.calculator.viewmodel.CalculatorScreenViewModel

@Composable
fun CalcButton(
    button: CalculatorButtons,
){
    val viewModel  = viewModel<CalculatorScreenViewModel>()
    Box(
        modifier = Modifier
            .padding(10.dp)
            .size(70.dp)
            .clip(CircleShape)
            .background(
                if (button.type == ButtonType.NUMERIC) MaterialTheme.colorScheme.background
                else MaterialTheme.colorScheme.secondaryContainer
            )
            .clickable(
                onClick = { viewModel.onAction(button.onAction) }
            ),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = button.label,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.titleMedium,
            fontSize = 24.sp
        )
    }
}

@Composable
fun ButtonsLayout(
    buttons: List<CalculatorButtons>,
){
    LazyVerticalGrid(
        columns = GridCells.Fixed(4),
        verticalArrangement = Arrangement.spacedBy(1.dp),
        horizontalArrangement = Arrangement.spacedBy(1.dp),
        modifier = Modifier
            .padding(10.dp)
    ){
        items( buttons ) {item ->
            CalcButton(button = item)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalcButtonPreview(){
    Column {
//        CalcButton(button = CalculatorButtons(5,"5",ButtonType.NUMERIC,)) {}
//        ButtonsLayout(buttons = AllButtons)
    }
}