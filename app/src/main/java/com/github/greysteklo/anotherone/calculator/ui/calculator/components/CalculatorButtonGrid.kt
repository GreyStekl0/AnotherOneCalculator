package com.github.greysteklo.anotherone.calculator.ui.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import com.github.greysteklo.anotherone.calculator.R
import com.github.greysteklo.anotherone.calculator.ui.calculator.CalculatorAction

data class GridButtonData(
    val content: ButtonContent,
    val action: CalculatorAction,
)

private val calculatorButtonLayout: List<List<GridButtonData>> =
    listOf(
        listOf(
            GridButtonData(ButtonContent(text = "AC", fontSize = 30.sp), CalculatorAction.Clear),
            GridButtonData(
                ButtonContent(text = "( )", fontSize = 40.sp),
                CalculatorAction.Parentheses,
            ),
            GridButtonData(ButtonContent(text = "%"), CalculatorAction.Percent),
            GridButtonData(ButtonContent(text = "/"), CalculatorAction.Operation("/")),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "7"), CalculatorAction.Number(7)),
            GridButtonData(ButtonContent(text = "8"), CalculatorAction.Number(8)),
            GridButtonData(ButtonContent(text = "9"), CalculatorAction.Number(9)),
            GridButtonData(ButtonContent(text = "X"), CalculatorAction.Operation("*")),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "4"), CalculatorAction.Number(4)),
            GridButtonData(ButtonContent(text = "5"), CalculatorAction.Number(5)),
            GridButtonData(ButtonContent(text = "6"), CalculatorAction.Number(6)),
            GridButtonData(ButtonContent(text = "-"), CalculatorAction.Operation("-")),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "1"), CalculatorAction.Number(1)),
            GridButtonData(ButtonContent(text = "2"), CalculatorAction.Number(2)),
            GridButtonData(ButtonContent(text = "3"), CalculatorAction.Number(3)),
            GridButtonData(ButtonContent(text = "+"), CalculatorAction.Operation("+")),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "0"), CalculatorAction.Number(0)),
            GridButtonData(ButtonContent(text = ","), CalculatorAction.Decimal),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.backspace,
                    contentDescription = "Delete",
                ),
                CalculatorAction.Delete,
            ),
            GridButtonData(ButtonContent(text = "="), CalculatorAction.Equally),
        ),
    )

typealias OnCalculatorAction = (CalculatorAction) -> Unit

@Composable
fun CalculatorButtonGrid(
    onAction: OnCalculatorAction,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        calculatorButtonLayout.forEach { rowButtons ->
            Row(
                modifier =
                    Modifier
                        .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
            ) {
                rowButtons.forEach { buttonData ->
                    CalculatorButton(
                        onClick = { onAction(buttonData.action) },
                        content = buttonData.content,
                    )
                }
            }
        }
    }
}
