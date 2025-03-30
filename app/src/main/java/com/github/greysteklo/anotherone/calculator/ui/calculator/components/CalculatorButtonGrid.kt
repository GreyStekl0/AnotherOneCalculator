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

typealias OnCalculatorAction = (CalculatorAction) -> Unit

@Composable
fun CalculatorButtonGrid(
    onAction: OnCalculatorAction,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Clear) },
                content = ButtonContent(text = "AC", fontSize = 30.sp),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Parentheses) },
                content = ButtonContent(text = "( )", fontSize = 40.sp),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Percent) },
                content = ButtonContent(text = "%"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Operation("/")) },
                content = ButtonContent(text = "/"),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(7)) },
                content = ButtonContent(text = "7"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(8)) },
                content = ButtonContent(text = "8"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(9)) },
                content = ButtonContent(text = "9"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Operation("*")) },
                content = ButtonContent(text = "X"),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(4)) },
                content = ButtonContent(text = "4"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(5)) },
                content = ButtonContent(text = "5"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(6)) },
                content = ButtonContent(text = "6"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Operation("-")) },
                content = ButtonContent(text = "-"),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(6)) },
                content = ButtonContent(text = "1"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(6)) },
                content = ButtonContent(text = "2"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(6)) },
                content = ButtonContent(text = "3"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Operation("+")) },
                content = ButtonContent(text = "+"),
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Number(0)) },
                content = ButtonContent(text = "0"),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Decimal) },
                content = ButtonContent(text = ","),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Delete) },
                content =
                    ButtonContent(
                        iconResId = R.drawable.backspace,
                        contentDescription = "Delete", // Можно переопределить описание
                    ),
            )
            CalculatorButton(
                onClick = { onAction(CalculatorAction.Equally) },
                content = ButtonContent(text = "="),
            )
        }
    }
}
