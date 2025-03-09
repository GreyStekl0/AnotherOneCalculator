package io.github.greystekl0.anotherone.calculator.ui.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NumberButton(
    number: String,
    example: String,
    onUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {
            val newValue = if (example == "0") number else example + number
            onUpdate(newValue)
        },
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
    ) {
        Text(number, fontSize = 30.sp)
    }
}

@Composable
fun OperatorButton(
    operator: String,
    example: String,
    onUpdate: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Button(
        onClick = {
            val newExample =
                if (example.last() in ",+-*/") {
                    example.dropLast(1) + operator
                } else if (example.last().toString() != operator) {
                    example + operator
                } else {
                    example
                }
            onUpdate(newExample)
        },
        shape = RoundedCornerShape(10.dp),
        modifier = modifier,
    ) {
        Text(operator, fontSize = 30.sp)
    }
}
