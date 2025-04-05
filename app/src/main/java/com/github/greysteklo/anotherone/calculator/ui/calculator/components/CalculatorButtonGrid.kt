package com.github.greysteklo.anotherone.calculator.ui.calculator.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.github.greysteklo.anotherone.calculator.R
import com.github.greysteklo.anotherone.calculator.ui.calculator.CalculatorAction

data class GridButtonData(
    val content: ButtonContent,
    val action: CalculatorAction,
)

private val calculatorButtonLayout: List<List<GridButtonData>> =
    listOf(
        listOf(
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.ac,
                    contentDescription = "AC",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Clear,
            ),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.staples,
                    contentDescription = "staples",
                    imageSize = 70.dp,
                ),
                CalculatorAction.Parentheses,
            ),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.percent,
                    contentDescription = "percent",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Percent,
            ),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.obelus,
                    contentDescription = "obelus",
                    imageSize = 150.dp,
                ),
                CalculatorAction.Operation("÷"),
            ),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "7"), CalculatorAction.Number(7)),
            GridButtonData(ButtonContent(text = "8"), CalculatorAction.Number(8)),
            GridButtonData(ButtonContent(text = "9"), CalculatorAction.Number(9)),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.multiplication,
                    contentDescription = "multiplication",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Operation("×"),
            ),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "4"), CalculatorAction.Number(4)),
            GridButtonData(ButtonContent(text = "5"), CalculatorAction.Number(5)),
            GridButtonData(ButtonContent(text = "6"), CalculatorAction.Number(6)),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.minus,
                    contentDescription = "minus",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Operation("-"),
            ),
        ),
        listOf(
            GridButtonData(ButtonContent(text = "1"), CalculatorAction.Number(1)),
            GridButtonData(ButtonContent(text = "2"), CalculatorAction.Number(2)),
            GridButtonData(ButtonContent(text = "3"), CalculatorAction.Number(3)),
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.plus,
                    contentDescription = "plus",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Operation("+"),
            ),
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
            GridButtonData(
                ButtonContent(
                    iconResId = R.drawable.equal,
                    contentDescription = "equal",
                    imageSize = 90.dp,
                ),
                CalculatorAction.Equally,
            ),
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
