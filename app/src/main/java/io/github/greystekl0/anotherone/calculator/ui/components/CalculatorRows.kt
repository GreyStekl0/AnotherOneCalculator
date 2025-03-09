package io.github.greystekl0.anotherone.calculator.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.github.greystekl0.anotherone.calculator.R
import io.github.greystekl0.anotherone.calculator.domain.calculate
import io.github.greystekl0.anotherone.calculator.model.NumberRowConfig

@Composable
fun TopButtonRow(
    example: String,
    onUpdate: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Button(
            onClick = { onUpdate("0") },
            modifier =
                Modifier
                    .weight(1f)
                    .aspectRatio(1f),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("C", fontSize = 30.sp)
        }

        Button(
            onClick = {
                onUpdate(if (example.length == 1) "0" else example.dropLast(1))
            },
            modifier =
                Modifier
                    .weight(1f)
                    .aspectRatio(1f),
            shape = RoundedCornerShape(10.dp),
        ) {
            Image(painterResource(R.drawable.backspace), contentDescription = "backspace")
        }

        Button(
            onClick = {
                val result = calculate("$example / 100")
                onUpdate(result)
            },
            modifier =
                Modifier
                    .weight(1f)
                    .aspectRatio(1f),
            shape = RoundedCornerShape(10.dp),
        ) {
            Text("%", fontSize = 30.sp)
        }

        OperatorButton(
            "/",
            example,
            onUpdate,
            Modifier
                .weight(1f)
                .aspectRatio(1f),
        )
    }
}

@Composable
fun NumberRow(
    example: String,
    onUpdate: (String) -> Unit,
    config: NumberRowConfig,
    modifier: Modifier = Modifier,
) {
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        NumberButton(
            config.number1,
            example,
            onUpdate,
            Modifier
                .weight(1f)
                .aspectRatio(1f),
        )
        NumberButton(
            config.number2,
            example,
            onUpdate,
            Modifier
                .weight(1f)
                .aspectRatio(1f),
        )
        NumberButton(
            config.number3,
            example,
            onUpdate,
            Modifier
                .weight(1f)
                .aspectRatio(1f),
        )
        OperatorButton(
            config.operator,
            example,
            onUpdate,
            Modifier
                .weight(1f)
                .aspectRatio(1f),
        )
    }
}

@Composable
fun BottomButtonRow(
    example: String,
    result: String,
    onUpdate: (String) -> Unit,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
    ) {
        Button(
            onClick = { },
            shape = RoundedCornerShape(10.dp),
            modifier =
                Modifier
                    .weight(1f)
                    .aspectRatio(1f),
        ) { Text("?") }

        NumberButton(
            "0",
            example,
            onUpdate,
            Modifier
                .weight(1f)
                .aspectRatio(1f),
        )

        Button(
            onClick = {
                val operators = listOf('+', '-', '*', '/')
                val lastOperatorIndex = example.indexOfLast { it in operators }
                val currentOperand =
                    if (lastOperatorIndex == -1) {
                        example
                    } else {
                        example.substring(
                            lastOperatorIndex + 1,
                        )
                    }

                val newExample =
                    if (',' !in currentOperand && example.last() !in ",+-*/") {
                        "$example,"
                    } else {
                        example
                    }
                onUpdate(newExample)
            },
            shape = RoundedCornerShape(10.dp),
            modifier =
                Modifier
                    .weight(1f)
                    .aspectRatio(1f),
        ) { Text(",", fontSize = 30.sp) }

        Button(
            onClick = { onUpdate(result) },
            shape = RoundedCornerShape(10.dp),
            modifier =
                Modifier
                    .weight(1f)
                    .aspectRatio(1f),
        ) { Text("=", fontSize = 30.sp) }
    }
}
