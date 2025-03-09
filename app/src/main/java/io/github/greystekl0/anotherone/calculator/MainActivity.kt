package io.github.greystekl0.anotherone.calculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import io.github.greystekl0.anotherone.calculator.ui.theme.AnotherOneCalculatorTheme
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AnotherOneCalculatorTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Calculator(innerPadding)
                }
            }
        }
    }
}

@Composable
private fun NumberButton(
    number: String,
    example: String,
    onUpdate: (String) -> Unit,
) {
    Button(onClick = {
        val newValue = if (example == "0") number else example + number
        onUpdate(newValue)
    }) {
        Text(number)
    }
}

@Composable
private fun OperatorButton(
    operator: String,
    example: String,
    onUpdate: (String) -> Unit,
) {
    Button(onClick = {
        val newExample =
            if (example.last() in ",+-*/") {
                example.dropLast(1) + operator
            } else if (example.last().toString() != operator) {
                example + operator
            } else {
                example
            }
        onUpdate(newExample)
    }) {
        Text(operator)
    }
}

@Preview(showBackground = true)
@Composable
private fun Calculator(innerPadding: PaddingValues = PaddingValues()) {
    var example by remember { mutableStateOf("0") }
    var result by remember { mutableStateOf("0") }

    fun updateExampleAndCalculate(newExample: String) {
        example = newExample
        result = calculate(example)
    }

    Column(
        modifier =
            Modifier
                .padding(innerPadding)
                .fillMaxSize(),
    ) {
        Text(text = example, fontSize = 30.sp)
        Text(text = "= $result", fontSize = 30.sp)

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { updateExampleAndCalculate("0") }) { Text("C") }

            Button(onClick = {
                updateExampleAndCalculate(if (example.length == 1) "0" else example.dropLast(1))
            }) {
                Image(painterResource(R.drawable.backspace), contentDescription = "backspace")
            }

            Button(onClick = {
                result = calculate("$example / 100")
                example = result
            }) { Text("%") }

            OperatorButton("/", example, ::updateExampleAndCalculate)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            NumberButton("7", example, ::updateExampleAndCalculate)
            NumberButton("8", example, ::updateExampleAndCalculate)
            NumberButton("9", example, ::updateExampleAndCalculate)
            OperatorButton("*", example, ::updateExampleAndCalculate)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            NumberButton("4", example, ::updateExampleAndCalculate)
            NumberButton("5", example, ::updateExampleAndCalculate)
            NumberButton("6", example, ::updateExampleAndCalculate)
            OperatorButton("-", example, ::updateExampleAndCalculate)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            NumberButton("1", example, ::updateExampleAndCalculate)
            NumberButton("2", example, ::updateExampleAndCalculate)
            NumberButton("3", example, ::updateExampleAndCalculate)
            OperatorButton("+", example, ::updateExampleAndCalculate)
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { }) { Text("?") }
            NumberButton("0", example, ::updateExampleAndCalculate)

            Button(onClick = {
                val operators = listOf('+', '-', '*', '/')
                val lastOperatorIndex = example.indexOfLast { it in operators }
                val currentOperand =
                    if (lastOperatorIndex == -1) example else example.substring(lastOperatorIndex + 1)

                val newExample =
                    if (',' !in currentOperand && example.last() !in ",+-*/") {
                        "$example,"
                    } else {
                        example
                    }
                updateExampleAndCalculate(newExample)
            }) { Text(",") }

            Button(onClick = { example = result }) { Text("=") }
        }
    }
}

fun calculate(expression: String): String {
    val sanitizedExpression = expression.removeSuffix()

    return try {
        val result = ExpressionBuilder(sanitizedExpression.replace(',', '.')).build().evaluate()
        result.formatResult()
    } catch (_: Exception) {
        "ERROR"
    }
}

private fun String.removeSuffix(): String = if (last() in ",+-*/") dropLast(1) else this

private fun Double.formatResult(): String =
    if (this % 1 == 0.0) {
        toInt().toString()
    } else {
        toString()
    }
