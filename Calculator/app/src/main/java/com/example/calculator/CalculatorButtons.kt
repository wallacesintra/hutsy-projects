package com.example.calculator

enum class ButtonType {
    NUMERIC, OPERATOR,
}

data class CalculatorButtons(
    val value: Int?,
    val label: String,
    val type: ButtonType,
    val onAction: CalculatorActions,
)


val AllButtons = listOf<CalculatorButtons>(
    CalculatorButtons(null,"AC",ButtonType.OPERATOR, CalculatorActions.Clear),
    CalculatorButtons(null,"DEL",ButtonType.OPERATOR, CalculatorActions.Delete),
    CalculatorButtons(null,"*",ButtonType.OPERATOR,CalculatorActions.Operation(CalculatorOperations.Multiply)),
    CalculatorButtons(null,"/",ButtonType.OPERATOR,CalculatorActions.Operation(CalculatorOperations.Divide)),
    CalculatorButtons(7,"7",ButtonType.NUMERIC,CalculatorActions.Number(7)),
    CalculatorButtons(8,"8",ButtonType.NUMERIC,CalculatorActions.Number(8)),
    CalculatorButtons(9,"9",ButtonType.NUMERIC,CalculatorActions.Number(9)),
    CalculatorButtons(null,"-",ButtonType.OPERATOR,CalculatorActions.Operation(CalculatorOperations.Subtract)),
    CalculatorButtons(4,"4",ButtonType.NUMERIC,CalculatorActions.Number(4)),
    CalculatorButtons(5,"5",ButtonType.NUMERIC,CalculatorActions.Number(5)),
    CalculatorButtons(6,"6",ButtonType.NUMERIC,CalculatorActions.Number(6)),
    CalculatorButtons(null,"+",ButtonType.OPERATOR,CalculatorActions.Operation(CalculatorOperations.Add)),
    CalculatorButtons(1,"1",ButtonType.NUMERIC,CalculatorActions.Number(1)),
    CalculatorButtons(2,"2",ButtonType.NUMERIC,CalculatorActions.Number(2)),
    CalculatorButtons(3,"3",ButtonType.NUMERIC,CalculatorActions.Number(3)),
    CalculatorButtons(null,"=",ButtonType.OPERATOR,CalculatorActions.Calculate),
    CalculatorButtons(null, ".",ButtonType.NUMERIC,CalculatorActions.Decimal),
    CalculatorButtons(0,"0",ButtonType.NUMERIC,CalculatorActions.Number(0))
)