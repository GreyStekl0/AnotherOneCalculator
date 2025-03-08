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

@Preview(showBackground = true)
@Composable
private fun Calculator(innerPadding: PaddingValues = PaddingValues()) {
    var example by
        remember {
            mutableStateOf("0")
        }
    var result by
        remember {
            mutableStateOf("0")
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
            Button(onClick = { example = "0" }) {
                Text("AC")
            }
            Button(onClick = {
                example =
                    if (example.length == 1) {
                        "0"
                    } else {
                        example.dropLast(1)
                    }
            }) {
                Image(
                    painter = painterResource(R.drawable.backspace),
                    contentDescription = "backspace",
                )
            }
            Button(onClick = { example += "%" }) {
                Text("%")
            }
            Button(onClick = { if (example.last() != '/') example += "/" }) {
                Text("/")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { if (example == "0") example = "7" else example += "7" }) {
                Text("7")
            }
            Button(onClick = { if (example == "0") example = "8" else example += "8" }) {
                Text("8")
            }
            Button(onClick = { if (example == "0") example = "9" else example += "9" }) {
                Text("9")
            }
            Button(onClick = { if (example.last() != '*') example += "*" }) {
                Text("*")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { if (example == "0") example = "4" else example += "4" }) {
                Text("4")
            }
            Button(onClick = { if (example == "0") example = "5" else example += "5" }) {
                Text("5")
            }
            Button(onClick = { if (example == "0") example = "6" else example += "6" }) {
                Text("6")
            }
            Button(onClick = { if (example.last() != '-') example += "-" }) {
                Text("-")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { if (example == "0") example = "1" else example += "1" }) {
                Text("1")
            }
            Button(onClick = { if (example == "0") example = "2" else example += "2" }) {
                Text("2")
            }
            Button(onClick = { if (example == "0") example = "3" else example += "3" }) {
                Text("3")
            }
            Button(onClick = { if (example.last() != '+') example += "+" }) {
                Text("+")
            }
        }
        Row(modifier = Modifier.fillMaxWidth()) {
            Button(onClick = { }) {
                Text("?")
            }
            Button(onClick = { if (example == "0") example = "0" else example += "0" }) {
                Text("0")
            }
            Button(onClick = { if (example.last() != ',') example += "," }) {
                Text(",")
            }
            Button(onClick = {
                val temp =
                    calculate(if (example.last() in ",+-*/") example.dropLast(1) else example)
                result =
                    if (temp == null) {
                        "ERROR"
                    } else if (temp % 1 == 0.0) {
                        temp.toInt().toString()
                    } else {
                        temp.toString()
                    }
            }) {
                Text("=")
            }
        }
    }
}

fun calculate(expression: String): Double? =
    try {
        ExpressionBuilder(expression).build().evaluate()
    } catch (_: Exception) {
        null
    }
