package com.example.calculator

enum class ButtonType {
    NUMERIC, OPERATOR,
}

data class CalculatorButtons(
    val value: Int?,
    val label: String,
    val type: ButtonType
)


val AllButtons = listOf<CalculatorButtons>(
    CalculatorButtons(null,"AC",ButtonType.OPERATOR),
    CalculatorButtons(null,"DEL",ButtonType.OPERATOR),
    CalculatorButtons(null,"*",ButtonType.OPERATOR),
    CalculatorButtons(null,"/",ButtonType.OPERATOR),
    CalculatorButtons(7,"7",ButtonType.NUMERIC),
    CalculatorButtons(8,"8",ButtonType.NUMERIC),
    CalculatorButtons(9,"9",ButtonType.NUMERIC),
    CalculatorButtons(null,"-",ButtonType.OPERATOR),
    CalculatorButtons(4,"4",ButtonType.NUMERIC),
    CalculatorButtons(5,"5",ButtonType.NUMERIC),
    CalculatorButtons(6,"6",ButtonType.NUMERIC),
    CalculatorButtons(null,"+",ButtonType.OPERATOR),
    CalculatorButtons(1,"1",ButtonType.NUMERIC),
    CalculatorButtons(2,"2",ButtonType.NUMERIC),
    CalculatorButtons(3,"3",ButtonType.NUMERIC),
    CalculatorButtons(null,"=",ButtonType.OPERATOR),
    CalculatorButtons(null, ".",ButtonType.NUMERIC),
    CalculatorButtons(0,"0",ButtonType.NUMERIC)
)